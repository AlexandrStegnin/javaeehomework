package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.Tag;

import java.util.Collection;

public interface TagRepository {
    Collection<Tag> findAll();
    Tag findOne(String id);

    Tag remove(String tagId);

    Tag save(Tag tag);

    void saveAll(Collection<Tag> tags);

    Tag update(Tag tag);
}
