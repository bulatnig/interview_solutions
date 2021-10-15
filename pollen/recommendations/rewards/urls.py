"""
Django uses urls.py to match which view should be called
when you access specific url specified by a regexp
"""
from django.urls import path
from django.conf.urls import re_path

from .views import recommendations

urlpatterns = [
    re_path('recommendations/(?P<user_id>\d+)/', recommendations, name="recommendations"),
]
