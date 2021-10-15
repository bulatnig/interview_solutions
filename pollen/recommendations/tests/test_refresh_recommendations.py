import pytest

from rewards.management.commands.refresh_recommendations import refresh_recommendations
from rewards.models import Recommendation

import pytest
from django.core.management import call_command
from django.urls import reverse


@pytest.mark.django_db
@pytest.fixture(scope='session')
def django_db_setup(django_db_setup, django_db_blocker):
    with django_db_blocker.unblock():
        call_command('loaddata', 'example_data.json')


@pytest.mark.django_db
def test_refresh_recommendations():
    assert Recommendation.objects.all().delete()

    refresh_recommendations()

    assert Recommendation.objects.count() > 0

