package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Product;

import java.util.Collection;

public interface CartRepository {

    void deleteProduct(Product product);

    void addProduct(Product product);

    Collection<Product> getAll();

    void endPurchase();
}
