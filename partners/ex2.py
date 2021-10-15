#
# Complete the 'getAuthorHistory' function below.
#
# The function is expected to return a STRING_ARRAY.
# The function accepts STRING author as parameter.
#
# Base urls:
#   https://jsonmock.hackerrank.com/api/article_users?username=
#   https://jsonmock.hackerrank.com/api/articles?author=
#
import json
from typing import Generator, Dict, List, Optional
from urllib.request import urlopen


def getAuthorHistory(author: str) -> List[str]:
    return get_user_about(author) + get_article_titles(author)


def get_user_about(author: str) -> List[str]:
    abouts = []
    for data in consume("https://jsonmock.hackerrank.com/api/article_users?username=" + author):
        for item in data:
            append_if_present(abouts, item.get("about"))
    return abouts


def get_article_titles(author: str) -> List[str]:
    titles = []
    for data in consume("https://jsonmock.hackerrank.com/api/articles?author=" + author):
        for item in data:
            append_if_present(titles, item.get("title"))
            append_if_present(titles, item.get("story_title"))
    return titles


def consume(url: str) -> Generator[Dict, None, None]:
    page = 1
    total_pages = None
    while True:
        with urlopen(f"{url}&page={page}") as response:
            content = json.loads(response.read().decode("UTF-8"))
            if total_pages is None:
                total_pages = content["total_pages"]
            yield content["data"]
        if page >= total_pages:
            break
        else:
            page += 1


def append_if_present(history: List[str], value: Optional[str]) -> None:
    if value:
        history.append(value)
