package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.CartRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class CartService implements CartRepository {
    @NotNull
    private Map<String, Product> cart = new LinkedHashMap<>();

    @Override
    public void deleteProduct(String id) {
        cart.remove(id);
    }

    @Override
    public void addProduct(Product product) {
        cart.putIfAbsent(product.getId(), product);
    }

    @Override
    public Collection<Product> getAll() {
        return cart.values();
    }
}
