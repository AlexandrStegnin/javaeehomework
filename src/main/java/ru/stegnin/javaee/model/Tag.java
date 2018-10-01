package ru.stegnin.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends AbstractEntity implements Serializable {
    private static final String PREFIX = "#";

    @NotNull
    @Size(min = 3, max = 15, message = "Название должно быть от 3 до 15 символов")
    @Column(nullable = false)
    private String name = "";

    @ManyToOne
    private Product product;

    public static class Builder {
        private Tag newTag;

        public Builder() {
            newTag = new Tag();
        }

        public Builder withName(String name) {
            newTag.name = PREFIX + name;
            return this;
        }

        public Tag build() {
            return newTag;
        }
    }

    public void setName(String name) {
        this.name = PREFIX + name;
    }
}
