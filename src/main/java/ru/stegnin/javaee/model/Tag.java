package ru.stegnin.javaee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@ToString(exclude = "products")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "products")
@JsonIgnoreProperties(value = "products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tag extends AbstractEntity implements Serializable {
    private static final String PREFIX = "#";

    @NotNull
    @Size(min = 3, max = 15, message = "Название должно быть от 3 до 15 символов")
    @Column(nullable = false)
    private String name = "";

    @ManyToMany(mappedBy = "tags")
    private List<Product> products;

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

    @PrePersist
    private void setName() {
        if (!name.contains(PREFIX)) name = PREFIX + name;
    }
}
