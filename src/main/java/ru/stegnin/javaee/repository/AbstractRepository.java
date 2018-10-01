package ru.stegnin.javaee.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractRepository {

    @PersistenceContext(name = "javaEEhwUnit")
    public EntityManager em;

    @Nullable
    public <T> T getEntity(@NotNull final TypedQuery<T> query) {
        final List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) return null;
        return resultList.get(0);
    }
}
