package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.TagRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Named
@ApplicationScoped
public class TagService implements TagRepository {
    @NotNull
    private Map<String, Tag> tags = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        Collection<Tag> tags = new LinkedHashSet<>();
        tags.add(new Tag.Builder().withName("Одежда для мальчиков")
                .build());
        tags.add(new Tag.Builder().withName("Одежда для девочек")
                .build());
        tags.add(new Tag.Builder().withName("Зимняя")
                .build());
        tags.add(new Tag.Builder().withName("Осенняя")
                .build());
        tags.add(new Tag.Builder().withName("Весенняя")
                .build());
        tags.add(new Tag.Builder().withName("Летняя")
                .build());
        saveAll(tags);
    }

    @Override
    public Collection<Tag> findAll() {
        return tags.values();
    }

    @Override
    public Tag findOne(String id) {
        return tags.get(id);
    }

    @Override
    public void delete(String id) {
        tags.remove(id);
    }

    @Override
    public void save(Tag tag) {
        tags.putIfAbsent(tag.getId(), tag);
    }

    @Override
    public void saveAll(Collection<Tag> tagsList) {
        tagsList.forEach(t -> tags.putIfAbsent(t.getId(), t));
    }

    @Override
    public void update(Tag tag) {
        tags.merge(tag.getId(), tag, (oldTag, newTag) -> newTag);
    }
}
