package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Product;

import javax.ejb.Local;
import java.util.Collection;

@Local
public interface CartRepository {

    void deleteProduct(Product product);

    void addProduct(Product product);

    Collection<Product> getAll();

    void endPurchase();
}
