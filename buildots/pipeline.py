import logging
from abc import ABC, abstractmethod
from pathlib import Path
from typing import Generator

import time

logger = logging.getLogger(__name__)

INPUT_CHECK_FREQUENCY_SECONDS = 5


class FileTaskPipeline(ABC):
    """Pipeline where tasks represented as files in input and output folders.

    I didn't have enough time to abstract a way output tasks/files are produced.
    They should be written in some temporary file first and then moved/renamed
    into output directory in kind of atomic operation. To avoid consumer further
    down the pipeline to read incomplete input file.
    """

    def __init__(self, input: str, output: str) -> None:
        self.input = input
        self.output = output

    def run(self):
        logger.info("pipeline_process_started")
        while True:
            logger.info("pipeline_cycle_started")
            self._check_input()
            logger.info("pipeline_cycle_finished")
            time.sleep(INPUT_CHECK_FREQUENCY_SECONDS)

    def _check_input(self):
        for file_path in self._collect_files():
            logger.info("task_processing_started %s", file_path.name)
            self.process_task(file_path)
            logger.info("task_processing_finished %s", file_path.name)
            # file_path.unlink()
            # logger.info("task_deleted %s", file_path.name)

    def _collect_files(self) -> Generator[Path, None, None]:
        entries = Path(self.input)
        for entry in entries.iterdir():
            yield entry

    @abstractmethod
    def process_task(self, file_path: Path):
        pass
