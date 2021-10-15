from typing import List


def find(angles: List[float], theta: float):
    if not angles:
        return 0

    left = 0
    max_count = 1
    right = 1
    while right < len(angles):
        min_left = angles[right] - theta  # 32
        while angles[left] < min_left:
            left += 1
        count = right - left + 1
        if count > max_count:
            max_count = count
        right += 1
    return max_count


assert 4 == find([1, 2, 3, 359], 5)
# assert 6 == find([31, 32, 33, 34, 35, 36, 37, 38, 39], 5)

