package org.bulatnig.supermarket.product;

public abstract class Product {

    private final Type type;
    private Long id;
    private String name;

    public Product(Type type) {
        this.type = type;
    }

    public abstract int calculatePrice(int quantity, int pencePerItem);

    public Type getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    enum Type {
        ENUMERABLE,
        WEIGHED
    }

}
