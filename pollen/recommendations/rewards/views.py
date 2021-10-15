"""
A view function, or view for short, is a Python function that takes a Web request and 
returns a Web response. This response can be the HTML contents of a Web page,
or a redirect, or a 404 error, or an XML document, or an image... or anything, really.
The view itself contains whatever arbitrary logic is necessary to return that response.
This code can live anywhere you want, as long as it’s on your Python path.
There’s no other requirement–no “magic,” so to speak. For the sake of putting the code
somewhere, the convention is to put views in a file called views.py,
placed in your project or application directory.
You can read more at https://docs.djangoproject.com/en/3.2/topics/http/views/
"""
from django.http import HttpResponseNotFound

from rest_framework.decorators import api_view
from rest_framework.response import Response

from .models import Recommendation
from .serializers import RecommendationSerializer


@api_view(['GET'])
def recommendations(request, user_id):
    if not user_id:
        return HttpResponseNotFound()

    recommendations =  Recommendation.objects.filter(user_id=user_id)

    if not recommendations:
        return HttpResponseNotFound()

    serializer = RecommendationSerializer(recommendations, many=True)

    return Response(serializer.data)