"""
django-rest-framework is not part of Django itself it's a separate toolkit for
building Web APIs on top of Django.
Serializers are providing a mechanism for 'translating' Django models into other 
formats. Usually these other formats will be text-based and used for sending Django data
over a wire, but it’s possible for a serializer to handle any format (text-based or not).
"""
from rest_framework import serializers

from .models import Recommendation


class RecommendationSerializer(serializers.ModelSerializer):

    class Meta:
        model = Recommendation
        exclude = ['id']