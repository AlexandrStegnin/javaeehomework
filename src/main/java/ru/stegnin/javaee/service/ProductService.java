package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
public class ProductService extends AbstractRepository implements ProductRepository {

    @Override
    public Collection<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.created DESC", Product.class).getResultList();
    }

    @Override
    public Product findOne(String id) {
        return em.find(Product.class, id);
    }

    @Nullable
    @Override
    public Product delete(@NotNull String productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            em.remove(product);
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product save(Product product) {
        return em.merge(product);
    }

    @Override
    public void saveAll(Collection<Product> productList) {
        productList.forEach(pl -> em.persist(pl));
    }

    @Override
    public Product update(Product product) {
        return em.merge(product);
    }



}
