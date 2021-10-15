from django.contrib.auth.mixins import LoginRequiredMixin
from django.views.generic import ListView, DetailView

from backoffice.models import Transaction


class TransactionListView(LoginRequiredMixin, ListView):
    model = Transaction


class TransactionDetailView(LoginRequiredMixin, DetailView):
    model = Transaction
