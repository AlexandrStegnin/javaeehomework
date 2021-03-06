package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.CartRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class CartService extends AbstractRepository implements CartRepository {
    @NotNull
    private List<Product> cart;

    @PostConstruct
    private void init() {
        cart = new ArrayList<>();
    }

    @Override
    public void deleteProduct(Product product) {
        cart.remove(product);
    }

    @Override
    public void addProduct(Product product) {
        cart.add(product);
    }

    @Override
    public Collection<Product> getAll() {
        return cart.subList(0, cart.size());
    }

    @Override
    public void endPurchase() {
        cart.forEach(c -> em.persist(c));
        cart.clear();
    }
}
