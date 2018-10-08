package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    Collection<Product> findAll();

    Product findOne(String id);

    Product remove(String productId);

    Product save(Product product);

    void saveAll(Collection<Product> products);

    Product update(Product product);

    List<Product> findByTag(String tagId);
}
