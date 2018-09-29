package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Tag;

import java.util.Collection;

public interface TagRepository {
    Collection<Tag> findAll();
    Tag findOne(String id);

    void delete(String id);

    void save(Tag tag);

    void saveAll(Collection<Tag> tags);

    void update(Tag tag);
}
