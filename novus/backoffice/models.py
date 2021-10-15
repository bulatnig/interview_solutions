from django.db import models


class Client(models.Model):
    name = models.CharField(max_length=100)


class Account(models.Model):
    client = models.ForeignKey(Client, on_delete=models.CASCADE)
    balance = models.DecimalField(max_digits=15, decimal_places=2)


class Transaction(models.Model):
    DIRECTIONS = (
        ('IN', 'Inbound'),
        ('OUT', 'Outbound'),
    )
    account = models.ForeignKey(Account, on_delete=models.CASCADE)
    amount = models.DecimalField(max_digits=15, decimal_places=2)
    currency = models.CharField(max_length=3)
    direction = models.CharField(max_length=3, choices=DIRECTIONS)
    counterparty_name = models.CharField(max_length=100)
    counterparty_account = models.PositiveIntegerField()
    counterparty_sort_code = models.PositiveIntegerField()
    created_at = models.DateTimeField(auto_now_add=True)


class Note(models.Model):
    transaction = models.ForeignKey(Transaction, on_delete=models.CASCADE)
    text = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)

