"""
django-rest-framework is not part of Django itself it's a separate toolkit for
building Web APIs on top of Django.
Serializers are providing a mechanism for 'translating' Django models into other 
formats. Usually these other formats will be text-based and used for sending Django data
over a wire, but it’s possible for a serializer to handle any format (text-based or not).
"""
from ticketing_api.models import Order

from rest_framework import serializers


class TicketSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    name = serializers.CharField()
    price = serializers.FloatField()
    reward_points = serializers.IntegerField()


class UserSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    name = serializers.CharField()
    points = serializers.IntegerField()


class OrderSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    user_id = serializers.IntegerField()
    tickets = TicketSerializer(many=True)
