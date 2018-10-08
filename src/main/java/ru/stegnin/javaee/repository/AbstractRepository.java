package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.util.KeyGenerator;
import ru.stegnin.javaee.util.SimpleKeyGenerator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository {

    @PersistenceContext(name = "javaEEhwUnit")
    public EntityManager em;

    protected KeyGenerator keyGenerator = new SimpleKeyGenerator();
}
