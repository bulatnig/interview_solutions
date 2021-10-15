"""
A module to get data from the ticketing service. You need to have ticketing running for it to work.

Usage:
>>> from rewards.ticketing_api_client import get_users
>>> get_users()
[{'id': 1, 'name': 'John Doe', 'points': 100}, {'id': 2, 'name': 'Jane Doe', 'points': 150}]
>>> from rewards.ticketing_api_client import get_orders
>>> get_orders(1)
[{'id': 1, 'user_id': 1, 'tickets': [...]
"""
import requests

BASE_URL = 'http://127.0.0.1:3000'


def get_orders(user_id):
    url = f'{BASE_URL}/getOrders/{user_id}'
    return requests.get(url).json()


def get_users():
    url = f'{BASE_URL}/users/'
    return requests.get(url).json()
