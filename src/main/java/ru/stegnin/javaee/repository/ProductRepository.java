package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Product;

import java.util.Collection;

public interface ProductRepository {
    Collection<Product> findAll();

    Product findOne(String id);

    void delete(Product product);

    void save(Product product);

    void saveAll(Collection<Product> products);

    Product update(Product product);
}
