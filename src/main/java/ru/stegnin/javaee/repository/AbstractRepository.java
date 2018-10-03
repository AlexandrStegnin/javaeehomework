package ru.stegnin.javaee.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository {

    @PersistenceContext(name = "javaEEhwUnit")
    public EntityManager em;
}
