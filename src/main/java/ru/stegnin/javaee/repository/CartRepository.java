package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Product;

import java.util.Collection;

public interface CartRepository {

    void deleteProduct(String id);

    void addProduct(Product product);

    Collection<Product> getAll();
}
