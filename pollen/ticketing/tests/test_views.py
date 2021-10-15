"""This just an example. You're not expected to make any changes here."""

import pytest
from django.core.management import call_command
from django.urls import reverse

from ticketing_api.models import Ticket, Order, User
from ticketing_api.serializers import OrderSerializer, UserSerializer
from ticketing_api.views import getOrders

pytestmark = pytest.mark.django_db


@pytest.fixture(scope='session')
def django_db_setup(django_db_setup, django_db_blocker):
    with django_db_blocker.unblock():
        call_command('loaddata', 'example_data.json')


def test_getOrders(client):
    orders = OrderSerializer(Order.objects.all(), many=True)
    for order in orders.data:
        order['user'] = UserSerializer(User.objects.get(id=order['user_id'])).data

    response = client.get(reverse('getOrders', args=('',)))
    assert response.status_code == 200
    assert response.json() == orders.data


def test_getOrders_user_1(client):
    orders = OrderSerializer(Order.objects.filter(user_id=1), many=True)
    for order in orders.data:
        order['user'] = UserSerializer(User.objects.get(id=order['user_id'])).data

    response = client.get(reverse('getOrders', args=(1,)))
    assert response.status_code == 200
    assert response.json() == orders.data