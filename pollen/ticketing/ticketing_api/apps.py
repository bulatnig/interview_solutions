"""
Django application registry maintains metadata in an AppConfig instance for each
installed application.
If you need more information about apps go to
https://docs.djangoproject.com/en/3.2/ref/applications/
"""
from django.apps import AppConfig


class TicketingApiConfig(AppConfig):
    name = 'ticketing_api'
