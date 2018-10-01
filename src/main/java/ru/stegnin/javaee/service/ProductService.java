package ru.stegnin.javaee.service;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Collection;

@Named
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

    @Override
    public void delete(Product product) {
        em.remove(em.find(Product.class, product.getId()));
    }

    @Override
    public void save(Product product) {
        em.persist(product);
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
