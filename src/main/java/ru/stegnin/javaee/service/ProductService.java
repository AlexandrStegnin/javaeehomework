package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.model.Product_;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.model.Tag_;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.repository.TagRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductService extends AbstractRepository implements ProductRepository {
    private Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    @Inject
    private TagRepository tagRepo;

    @Override
    public Collection<Product> findAll() {
        LOGGER.info("### findAll products ###");
        return em.createQuery("SELECT p FROM Product p ORDER BY p.created DESC", Product.class).getResultList();
    }

    @Override
    public Product findOne(String productId) {
        LOGGER.info("### findOne by productId : " + productId);
        return em.find(Product.class, productId);
    }

    @Nullable
    @Override
    public Product remove(@NotNull String productId) {
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
        Product oldProduct = em.find(Product.class, product.getId());
        if (product.getName() == null) {
            product.setName(oldProduct.getName());
        }
        if (product.getDescription() == null) {
            product.setDescription(oldProduct.getDescription());
        }
        if (product.getPrice() == null) {
            product.setPrice(oldProduct.getPrice());
        }
        if (product.getTags() == null) {
            product.setTags(oldProduct.getTags());
        }
        return em.merge(product);
    }

    @Override
    public List<Product> findByTag(String tagId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        Tag tag = tagRepo.findOne(tagId);
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Tag> join = root.join(Product_.tags, JoinType.INNER);
//        root.fetch(Product_.tags, JoinType.LEFT);
        criteriaQuery.where(
                builder.equal(join.get(Tag_.NAME), tag.getName()));

        TypedQuery<Product> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
