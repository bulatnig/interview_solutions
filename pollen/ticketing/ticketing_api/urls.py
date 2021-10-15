"""
Django uses urls.py to match which view should be called
when you access specific url specified by a regexp
"""
from django.urls import path
from ticketing_api import views
from django.conf.urls import re_path

urlpatterns = [
    re_path('getOrders/(?P<user_id>[0-9]*)', views.getOrders, name='getOrders'),
    re_path('users/', views.get_users),
    re_path('update-user', views.update_user),
]
