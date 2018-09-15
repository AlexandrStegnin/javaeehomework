package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@ApplicationScoped
public class ProductService implements ProductRepository {

    @NotNull
    private Map<String, Product> products = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        Collection<Product> products = new LinkedHashSet<>();
        products.add(new Product("Футболка", 299d));
        products.add(new Product("Шорты", 259d));
        products.add(new Product("Майка", 99d));
        products.add(new Product("Кросовки", 579d));
        products.add(new Product("Сапоги", 1299d));
        products.add(new Product("Куртка", 2999d));
        products.add(new Product("Пальто", 1329d));
        products.add(new Product("Штаны", 789d));
        products.add(new Product("Сандали", 239d));
        saveAll(products);
    }

    @Override
    public Collection<Product> findAll() {
        return products.values();
    }

    @Override
    public Product findOne(String id) {
        return products.get(id);
    }

    @Override
    public void delete(String id) {
        products.remove(id);
    }

    @Override
    public void save(Product product) {
        products.putIfAbsent(product.getId(), product);
    }

    @Override
    public void saveAll(Collection<Product> productList) {
        productList.forEach(pl -> products.putIfAbsent(pl.getId(), pl));
    }

    @Override
    public void update(Product product) {
        products.merge(product.getId(), product, (oldProduct, newProduct) -> newProduct);
    }
}
