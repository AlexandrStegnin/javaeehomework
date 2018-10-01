package ru.stegnin.javaee.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Nullable
    private LocalDate created;

    @Nullable
    private LocalDate updated;

    @PrePersist
    private void prePersist() {
        created = LocalDate.now();
        updated = LocalDate.now();
    }

    @PreUpdate
    private void preUpdate() {
        if (created == null) created = LocalDate.now();
        updated = LocalDate.now();
    }
}
