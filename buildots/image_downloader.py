import asyncio
import json
import logging
import uuid
from pathlib import Path
from typing import List

import aiofiles
import aiohttp
import click

# proper logging setup and configuration should be here
from pipeline import FileTaskPipeline

logging.basicConfig(format='%(asctime)s %(levelname)s %(message)s')
logging.getLogger().setLevel(logging.INFO)
logger = logging.getLogger(__name__)


class ImageDownloader(FileTaskPipeline):

    def __init__(self, input: str, output: str) -> None:
        super().__init__(input, output)

    def process_task(self, file_path: Path):
        """
        I decided to go file by file imagining each will contain lots of links.
        I treat file as a unit of work in which I download all links and delete the file.
        Collecting all the links from all the files is another viable option.

        Also I decided to delete a file for which all links where downloaded.
        If we want to keep the files we can rename them upon completion.

        If files should be kept in their original form we'll have to have some other
        way of tracking processed files, some persistent storage like database or file.

        Another benefit of having permanent storage is to be able to continue where
        we started. If processes abruptly finished before download all urls from file,
        after restart it can read the status and continue by downloading only
        missing files.
        """
        image_urls = self._collect_image_urls(file_path)
        self._download_images_in_parallel(image_urls)

    def _collect_image_urls(self, file_path: Path) -> List[str]:
        """
        We might want to add url validation here, at least very basic.

        See https://validators.readthedocs.io/en/latest/#module-validators.url
        """
        with file_path.open() as file:
            data = json.load(file)
            return data["image_list"]

    def _download_images_in_parallel(self, image_urls: List[str]):
        """
        If we are downloading images from the web, then different servers might
        have very different speeds.
        Few slow urls might add up drastically increasing total time taken.
        Async IO helps us mitigate that reducing total time to the time of slowest server.
        """
        asyncio.run(self.download_async(image_urls))

    async def download_async(self, image_urls: List[str]):
        async with aiohttp.ClientSession() as session:
            download_futures = [asyncio.create_task(self.download_file(session, url)) for url in image_urls]
            await asyncio.wait(download_futures)

    async def download_file(self, session: aiohttp.ClientSession, url: str):
        """
        We should decide if we want to follow HTTP redirects

        We should configure connection and request timeout for HTTP client.

        To create a file with correct extension we can't rely on URL, but
        should check returned Content-Type instead and map it to file extension.

        In case of failure we should log message with url and error details.
        """
        async with session.get(url) as response:
            assert response.status == 200
            file_name = f"{uuid.uuid4().hex}.jpg"
            async with aiofiles.open(f"{self.output}/{file_name}", mode='wb') as f:
                await f.write(await response.read())
                await f.close()
            logging.info("file_downloaded %s as %s", url, file_name)


@click.command()
@click.argument('input', type=click.Path(exists=True, file_okay=False))
@click.argument('output', type=click.Path(exists=True, file_okay=False, writable=True))
def start_image_downloader(input, output):
    """Copy contents of INPUT to OUTPUT."""
    ImageDownloader(input, output).run()


if __name__ == '__main__':
    start_image_downloader()
