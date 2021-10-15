from django.urls import path

from .views import TransactionListView, TransactionDetailView

urlpatterns = [
    path('', TransactionListView.as_view()),
    path('transactions/', TransactionListView.as_view(), name='transaction-list'),
    path('transactions/<int:pk>', TransactionDetailView.as_view(), name='transaction-details'),
]