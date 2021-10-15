"""
A model is the single, definitive source of information about your data.
It contains the essential fields and behaviors of the data you’re storing.
Generally, each model maps to a single database table.

The basics:

- Each model is a Python class that subclasses django.db.models.Model.
- Each attribute of the model represents a database field.
- With all of this, Django gives you an automatically-generated database-access API

If you need more details - https://docs.djangoproject.com/en/3.2/topics/db/models/
"""
from django.conf import settings
from django.db import models
from django.utils import timezone


class User(models.Model):
    name = models.TextField(null=True)
    points = models.IntegerField()


class Ticket(models.Model):
    name = models.CharField(max_length=100)
    price = models.FloatField()
    reward_points = models.IntegerField()


class Order(models.Model):
    user_id = models.IntegerField()
    tickets = models.ManyToManyField(Ticket)
