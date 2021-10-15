"""
Django uses urls.py to match which view should be called
when you access specific url specified by a regexp
"""
from django.conf.urls import re_path
from django.urls import include, path

urlpatterns = [
    re_path('', include('ticketing_api.urls')),
]
