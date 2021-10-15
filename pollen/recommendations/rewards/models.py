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
from django.db import models


class Reward(models.Model):
    name = models.CharField(max_length=100)
    points = models.PositiveIntegerField(default=0, null=False)
    max_per_user = models.PositiveIntegerField(default=1, null=False)


class Recommendation(models.Model):
    user_id = models.PositiveIntegerField(null=False)
    reward = models.ForeignKey(Reward)
    used_times
    visible
