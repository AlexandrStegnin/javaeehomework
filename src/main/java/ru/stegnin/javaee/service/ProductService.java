package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Named
@ApplicationScoped
public class ProductService implements ProductRepository {

    @NotNull
    private Map<String, Product> products = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        Collection<Product> products = new LinkedHashSet<>();
        products.add(new Product.Builder()
                .withName("Футболка")
                .withPrice(299d)
                .build());
        products.add(new Product.Builder()
                .withName("Шорты")
                .withPrice(259d)
                .build());
        products.add(new Product.Builder()
                .withName("Майка")
                .withPrice(99d)
                .build());
        products.add(new Product.Builder()
                .withName("Кросовки")
                .withPrice(579d)
                .build());
        products.add(new Product.Builder()
                .withName("Сапоги")
                .withPrice(1299d)
                .build());
        products.add(new Product.Builder()
                .withName("Куртка")
                .withPrice(2999d)
                .build());
        products.add(new Product.Builder()
                .withName("Пальто")
                .withPrice(1329d)
                .build());
        products.add(new Product.Builder()
                .withName("Штаны")
                .withPrice(789d)
                .build());
        products.add(new Product.Builder()
                .withName("Сандали")
                .withPrice(239d)
                .build());
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
