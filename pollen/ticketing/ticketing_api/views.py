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
from rest_framework.decorators import api_view
from rest_framework.response import Response

from ticketing_api.serializers import OrderSerializer, UserSerializer
from ticketing_api.models import *

@api_view(['GET', 'POST', 'PUT'])
def getOrders(request, user_id):
    if user_id == '':
        orders = OrderSerializer(Order.objects.all(), many=True)
    elif user_id != '':
        orders = OrderSerializer(Order.objects.filter(user_id=user_id), many=True)

    for order in orders.data:
        order['user'] = UserSerializer(User.objects.get(id=order['user_id'])).data

    return Response(orders.data)


@api_view(['GET', 'POST', 'PUT'])
def get_users(request):
    response = []

    for user in User.objects.all():
        response.append({
            'id': user.id,
            'name': user.name,
            'points': user.points,
        })

    return Response(response)


@api_view(['POST'])
def update_user(request):
    user = User.objects.get(id=request.data['user_id'])
    user.points += request.data['add_points']
    user.save()

    return Response({})
