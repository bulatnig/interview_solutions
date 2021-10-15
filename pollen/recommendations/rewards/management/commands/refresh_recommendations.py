"""
django-admin is Django’s command-line utility for administrative tasks. To run them with
DJANGO_SETTINGS_MODULE environment variable set you can call it running:

python manage.py <script_name>

Django allows to define your own management commands in management/commands/
which should inherit from BaseCommand and has handle function which is called when 
management command is being called.
"""
from django.core.management.base import BaseCommand
from django.db import transaction

from rewards.models import Recommendation, Reward
from rewards.ticketing_api_client import get_users, get_orders


def refresh_recommendations():
    rewards = Reward.objects.all()
    users = get_users()
    for user in users:
        _refresh_user_recommendations(rewards, user)


@transaction.atomic
def _refresh_user_recommendations(rewards, user):
    Recommendation.obje
    user_rewards = [reward.id for reward in rewards if reward.points <= user.points]

    Recommendation.objects.filter(user_id=user.id).except(rewards__in=user_rewards).update(visible=False)
    Recommendation.objects.bulk([])


class Command(BaseCommand):
    """Script to refresh recommendations."""

    def handle(self, *args, **options):
        refresh_recommendations()
