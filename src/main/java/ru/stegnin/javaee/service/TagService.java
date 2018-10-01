package ru.stegnin.javaee.service;

import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.TagRepository;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Named
@Stateless
public class TagService extends AbstractRepository implements TagRepository {

    @Override
    public Collection<Tag> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Tag> from = cq.from(Tag.class);
        cq.select(from);
        TypedQuery<Tag> query = em.createQuery(cq);
        List<Tag> tags = query.getResultList();
        return tags;
    }

    @Override
    public Tag findOne(String tagId) {
        return em.find(Tag.class, tagId);
    }

    @Override
    public void save(Tag tag) {
        em.persist(tag);
    }

    @Override
    public void saveAll(Collection<Tag> tagsList) {
        tagsList.forEach(t -> em.persist(t));
    }

    @Override
    public void update(Tag tag) {
        em.merge(tag);
    }

    @Override
    public void delete(Tag tag) {
        em.remove(em.find(Tag.class, tag.getId()));
    }
}
