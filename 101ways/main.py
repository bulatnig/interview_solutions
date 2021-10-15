# Help local Cafe. Produce schedule of dishes.
# Sandwich 60 seconds and 30 sec to serve
# 0:00 4 sandwich orders placed, start making sandwich 1
# 1:00 serve sandwich 1
# 1:30 make sandwich 2
# 2:30 serve sandwich 2
# 3:00 make sandwich 3
# 4:00 serve sandwich 3
# 4:30 make sandwich 4
# 5:30 serve sandwich 4
# 6:00 take a well earned break!

# Input integer = number of sandwiches
# Output is a a human readable text like above

# Potato 2.5 minutes to heat
# 30 seconds to put toppings
# 30 seconds to serve

# 0:00 Put jacket potato in microwave
# 0:00 Make sandwich 1
# 1:00 Serve sandwich 1
# 1:30 Make sandwich 2
# 2:30 Serve sandwich 2
# 3:00 take jacket potato out of microwave and top
# 3:30 serve jacket potato
# 4:00 take a break!

import math
from dataclasses import dataclass
from datetime import time
from enum import Enum
from typing import List

SERVE_TIME = 30  # number of seconds to serve a dish


class Dish(Enum):

    def __init__(self, make_seconds, microwave_seconds) -> None:
        self.make_seconds = make_seconds
        self.microwave_seconds = microwave_seconds

    SANDWICH = 60, 0
    POTATO = 0, 150


class OperationType(Enum):
    MAKE = 1
    SERVE = 2
    PUT_IN_MICROWAVE = 3
    TAKE_OUT_OF_MICROWAVE = 4


@dataclass
class Operation:
    start_time: time
    end_time: time
    type: OperationType


def schedule(sandwich_count: int, potato_count: int) -> List[Operation]:
    result = []
    time_seconds = 0
    while sandwich_count or potato_count:
        microwave_busy = False
        microwave_end_time = None
        if microwave_busy and time_seconds >= microwave_end_time:
            start_time = _to_time(time_seconds)
            time_seconds += 30
            make_end_time = _to_time(time_seconds)
            operation = Operation(start_time=start_time, end_time=make_end_time, type=OperationType.TOP_POTATO)
            result.append(operation)

            start_time = _to_time(time_seconds)
            time_seconds += 30
            make_end_time = _to_time(time_seconds)
            operation = Operation(start_time=start_time, end_time=make_end_time, type=OperationType.SERVE)
            result.append(operation)

            microwave_busy = False
        elif potato_count > 0:
            start_time = _to_time(time_seconds)
            time_seconds += 150
            make_end_time = _to_time(time_seconds)
            operation = Operation(start_time=start_time, end_time=make_end_time, type=OperationType.PUT_INTO_MICROWAVE)
            result.append(operation)
            microwave_busy = True
        if sandwich_count > 0:
            operations, end_time = _make_and_serve_sandwich(start_time)
            result += operations
            time_seconds = end_time
        elif microwave_busy:
            time_seconds = microwave_end_time
    return result


def build_schedule(number: int) -> List[Operation]:
    operations = []
    time_seconds = 0
    for i in range(number):
        start_time = _to_time(time_seconds)
        time_seconds += Dish.SANDWICH.value
        make_end_time = _to_time(time_seconds)
        operation = Operation(start_time=start_time, end_time=make_end_time, type=OperationType.MAKE)
        operations.append(operation)

        start_time = _to_time(time_seconds)
        time_seconds += SERVE_TIME
        make_end_time = _to_time(time_seconds)
        operation = Operation(start_time=start_time, end_time=make_end_time, type=OperationType.SERVE)
        operations.append(operation)
    return operations


def _to_time(seconds):
    return time(minute=math.floor(seconds / 60), second=seconds % 60)


def print_schedule(operations: List[Operation]):
    for operation in operations:
        if OperationType.MAKE == operation.type:
            print(f"{operation.start_time} make sandwich")
        if OperationType.SERVE == operation.type:
            print(f"{operation.start_time} serve sandwich")
    print(f"{operations[-1].end_time} take a well earned break!")


def test_print_schedule():
    schedule = build_schedule(2)

    assert 4 == len(schedule)
    assert time(minute=0, second=0) == schedule[0].start_time
    assert time(minute=1, second=0) == schedule[0].end_time
    assert OperationType.MAKE == schedule[0].type

    assert time(minute=1, second=0) == schedule[1].start_time
    assert time(minute=1, second=30) == schedule[1].end_time
    assert OperationType.SERVE == schedule[1].type

    assert time(minute=2, second=30) == schedule[3].start_time
    assert time(minute=3, second=0) == schedule[3].end_time
    assert OperationType.SERVE == schedule[3].type


def test_output(capsys):
    schedule = build_schedule(4)

    print_schedule(schedule)

    captured = capsys.readouterr().out
    assert "00:00:00 make sandwich" in captured
    assert "00:01:00 serve sandwich" in captured
    assert "00:01:30 make sandwich" in captured
    assert "00:02:30 serve sandwich" in captured
    assert "00:06:00 take a well earned break!" in captured
