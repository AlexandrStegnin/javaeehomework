package ru.stegnin.javaee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(exclude = "tags")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "tags")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product extends AbstractEntity implements Serializable {

    @Nullable
    @Size(min = 3, max = 20, message = "Название должно быть от 3 до 20 символов")
    @Column(nullable = false)
    private String name;

    @Nullable
    @DecimalMax(value= "99999.9", message = "Цена не должна превышать 99999.9")
    @DecimalMin(value = "0.1", message = "Цена не должна быть меньше 0.1")
    @Column(nullable = false)
    private Double price;

    @Nullable
    @Size(min = 10, max = 100, message = "Описание должно быть от 10 до 100 символов")
    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    public static class Builder {
        private Product newProduct;
        public Builder() {
            newProduct = new Product();
        }

        public Builder withName(String name) {
            newProduct.name = name;
            return this;
        }

        public Builder withPrice(Double price) {
            newProduct.price = price;
            return this;
        }

        public Builder withDescription(String description) {
            newProduct.description = description;
            return this;
        }

        public Product build() {
            return newProduct;
        }
    }

    public void setTags(List<Tag> tags) {
        this.getTags().clear();
        this.getTags().addAll(tags);
    }

}
