#
# Complete the 'numPaths' function below.
#
# The function is expected to return an INTEGER.
# The function accepts 2D_INTEGER_ARRAY warehouse as parameter.
#
from typing import List


def numPaths(warehouse: List[List[int]]) -> int:
    return count_paths_recursively(warehouse, 0, 0)


def count_paths_recursively(warehouse: List[List[int]], row: int, column: int) -> int:
    if row >= len(warehouse) or column >= len(warehouse[0]):
        return 0
    if warehouse[row][column] == 0:
        return 0
    if row == len(warehouse) - 1 and column == len(warehouse[0]) - 1:
        return 1
    return count_paths_recursively(warehouse, row + 1, column) + count_paths_recursively(warehouse, row, column + 1)


def numPaths2(warehouse: List[List[int]]) -> int:
    if warehouse[0][0] == 0:
        return 0
    rows = len(warehouse)
    columns = len(warehouse[0])
    for column in range(1, columns):
        if warehouse[0][column] == 0:
            continue
        warehouse[0][column] = warehouse[0][column - 1]
    for row in range(1, rows):
        if warehouse[row][0] == 0:
            continue
        warehouse[row][0] = warehouse[row - 1][0]
    for row in range(1, rows):
        for column in range(1, columns):
            if warehouse[row][column] == 0:
                continue
            warehouse[row][column] = warehouse[row - 1][column] + warehouse[row][column - 1]
    return warehouse[-1][-1]


print(numPaths2([
    [1, 1, 0, 1],
    [1, 1, 1, 1],
]))
