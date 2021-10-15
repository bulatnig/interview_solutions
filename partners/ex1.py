#
# Complete the 'commonSubstring' function below.
#
# The function accepts following parameters:
#  1. STRING_ARRAY a
#  2. STRING_ARRAY b
#
from typing import List


def commonSubstring(a: List[str], b: List[str]) -> None:
    for i in range(len(a)):
        if containCommonCharacters(a[i], b[i]):
            print("YES")
        else:
            print("NO")


def containCommonCharacters(element1: str, element2: str) -> bool:
    element1UniqueCharacters = {}
    for character in list(element1):
        element1UniqueCharacters[character] = 1
    for character in list(element2):
        if character in element1UniqueCharacters:
            return True
    return False


commonSubstring(["ab", "cd", "ef"], ["af", "ee", "ef"])
