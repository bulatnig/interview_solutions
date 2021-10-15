# noinspection PyUnusedLocal
# skus = unicode string
from abc import ABC, abstractmethod
from dataclasses import dataclass
from enum import Enum, auto
from typing import Generator, List, Dict, Tuple


class Product(Enum):
    A = 50
    B = 30
    C = 20
    D = 15
    E = 40
    F = 10
    G = 20
    H = 10
    I = 35
    J = 60
    K = 70
    L = 90
    M = 15
    N = 40
    O = 10
    P = 50
    Q = 30
    R = 50
    S = 20
    T = 20
    U = 40
    V = 50
    W = 20
    X = 17
    Y = 20
    Z = 21

    def __init__(self, price: int) -> None:
        self._value_ = auto()
        self.price = price


class UnknownProductException(Exception):

    def __init__(self, name: str) -> None:
        super().__init__(f"Unknown product found: {name}")


class Condition(ABC):

    @abstractmethod
    def is_applicable(self, items: Dict[Product, int]) -> bool:
        pass

    @abstractmethod
    def applied(self, items: Dict[Product, int]):
        pass


@dataclass
class MultiBuy(Condition):
    products: List[Tuple[Product, int]]

    def is_applicable(self, items: Dict[Product, int]) -> bool:
        for product, count in self.products:
            if product not in items or items[product] < count:
                return False
        return True

    def applied(self, items: Dict[Product, int]) -> None:
        for product, count in self.products:
            items[product] -= count


def sorted_from_pricier_to_cheaper(products):
    return sorted(products, key=lambda product: product.price, reverse=True)


@dataclass
class GroupBuy(Condition):
    count: int
    products: List[Product]

    def is_applicable(self, items: Dict[Product, int]) -> bool:
        present = 0
        for product, count in items.items():
            if product in self.products:
                present += count
        return present >= self.count

    def applied(self, items: Dict[Product, int]) -> None:
        applied = 0
        for product in sorted_from_pricier_to_cheaper(self.products):
            if product in items:
                while items[product] > 0 and applied < self.count:
                    items[product] -= 1
                    applied += 1


class Discount(ABC):

    @abstractmethod
    def apply(self, items: Dict[Product, int]) -> int:
        pass

    @abstractmethod
    def per_item(self) -> float:
        pass


@dataclass
class FixPrice(Discount):
    product: Product
    count: int
    price: int

    def apply(self, items: Dict[Product, int]) -> int:
        discount = 0
        if self.product in items and items[self.product] >= self.count:
            discount = self.product.price * self.count - self.price
        return discount

    def per_item(self) -> float:
        return self.product.price - self.price / self.count


@dataclass
class GetFree(Discount):
    product: Product
    count: int

    def apply(self, items: Dict[Product, int]) -> int:
        discount = 0
        if self.product in items and items[self.product] >= self.count:
            discount = self.product.price * self.count
        return discount

    def per_item(self) -> float:
        return self.product.price


@dataclass
class GroupFixPrice(Discount):
    products: List[Product]
    count: int
    price: int

    def apply(self, items: Dict[Product, int]) -> int:
        applied = 0
        total = 0
        for product in sorted_from_pricier_to_cheaper(self.products):
            if product in items:
                for i in range(min(items[product], self.count - applied)):
                    total += product.price
                    applied += 1
            if applied == self.count:
                break
        discount = total - self.price
        return discount

    def per_item(self) -> float:
        product = sorted_from_pricier_to_cheaper(self.products)[0]
        return product.price - self.price / self.count


@dataclass
class Offer:
    condition: Condition
    discount: Discount

    def is_applicable(self, items: Dict[Product, int]) -> bool:
        return self.condition.is_applicable(items)

    def apply(self, items: Dict[Product, int]) -> Tuple[bool, int]:
        discount = 0
        if self.condition.is_applicable(items):
            discount = self.discount.apply(items)
            if discount > 0:
                self.condition.applied(items)
        return discount > 0, discount

    def __lt__(self, other):
        return self.discount.per_item() < other.discount.per_item()


class Basket:

    def __init__(self) -> None:
        self.items: Dict[Product, int] = {}

    def add_item(self, product: Product) -> None:
        count = self.items.get(product)
        if count is None:
            count = 0
        count += 1
        self.items[product] = count

    def checkout(self, offers: List[Offer]) -> int:
        total = self._calculate_total()
        discount = self._calculate_total_discount(offers)
        return total - discount

    def _calculate_total(self) -> int:
        total = 0
        for product, count in self.items.items():
            total += product.price * count
        return total

    def _calculate_total_discount(self, offers: List[Offer]) -> int:
        items = self.items.copy()
        total_discount = 0
        for offer in offers:
            while offer.is_applicable(items):
                applied, discount = offer.apply(items)
                if not applied:
                    break
                total_discount += discount
        return total_discount


def get_offers() -> List[Offer]:
    return sorted([
        Offer(MultiBuy([(Product.A, 3)]), FixPrice(Product.A, 3, 130)),
        Offer(MultiBuy([(Product.A, 5)]), FixPrice(Product.A, 5, 200)),
        Offer(MultiBuy([(Product.B, 2)]), FixPrice(Product.B, 2, 45)),
        Offer(MultiBuy([(Product.E, 2), (Product.B, 1)]), GetFree(Product.B, 1)),
        Offer(MultiBuy([(Product.F, 3)]), GetFree(Product.F, 1)),
        Offer(MultiBuy([(Product.H, 5)]), FixPrice(Product.H, 5, 45)),
        Offer(MultiBuy([(Product.H, 10)]), FixPrice(Product.H, 10, 80)),
        Offer(MultiBuy([(Product.K, 2)]), FixPrice(Product.K, 2, 120)),
        Offer(MultiBuy([(Product.N, 3), (Product.M, 1)]), GetFree(Product.M, 1)),
        Offer(MultiBuy([(Product.P, 5)]), FixPrice(Product.P, 5, 200)),
        Offer(MultiBuy([(Product.Q, 3)]), FixPrice(Product.Q, 3, 80)),
        Offer(MultiBuy([(Product.R, 3), (Product.Q, 1)]), GetFree(Product.Q, 1)),
        Offer(MultiBuy([(Product.U, 4)]), GetFree(Product.U, 1)),
        Offer(MultiBuy([(Product.V, 2)]), FixPrice(Product.V, 2, 90)),
        Offer(MultiBuy([(Product.V, 3)]), FixPrice(Product.V, 3, 130)),
        Offer(GroupBuy(3, [Product.S, Product.T, Product.X, Product.Y, Product.Z]),
              GroupFixPrice([Product.S, Product.T, Product.X, Product.Y, Product.Z], 3, 45)),
    ], reverse=True)


def checkout(skus: str):
    basket = Basket()
    try:
        for product in parse_products(skus):
            basket.add_item(product)
    except UnknownProductException:
        return -1
    return basket.checkout(get_offers())


def parse_products(skus: str) -> Generator[Product, None, None]:
    for sku in list(skus):
        try:
            yield Product[sku]
        except KeyError:
            raise UnknownProductException(sku)
