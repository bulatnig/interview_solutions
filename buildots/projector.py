import logging
from pathlib import Path

import click
import imageio as im
import numpy as np

# proper logging setup and configuration should be here
from nfov import NFOV
from pipeline import FileTaskPipeline

logging.basicConfig(format='%(asctime)s %(levelname)s %(message)s')
logging.getLogger().setLevel(logging.INFO)
logger = logging.getLogger(__name__)


class Projector(FileTaskPipeline):

    def __init__(self, input: str, output: str) -> None:
        super().__init__(input, output)

    def process_task(self, file_path: Path):
        img = im.imread(file_path)
        nfov = NFOV()
        center_point = np.array([0.2, .5])  # camera center point (valid range [0,1])
        result = nfov.toNFOV(img, center_point)
        im.imwrite(f"{self.output}/02_{file_path.name}", result)
        center_point = np.array([0.7, .5])  # camera center point (valid range [0,1])
        result = nfov.toNFOV(img, center_point)
        im.imwrite(f"{self.output}/07_{file_path.name}", result)


@click.command()
@click.argument('input', type=click.Path(exists=True, file_okay=False))
@click.argument('output', type=click.Path(exists=True, file_okay=False, writable=True))
def start_image_downloader(input, output):
    """Copy contents of INPUT to OUTPUT."""
    Projector(input, output).run()


if __name__ == '__main__':
    start_image_downloader()
