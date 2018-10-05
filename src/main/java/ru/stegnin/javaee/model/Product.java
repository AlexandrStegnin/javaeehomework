package ru.stegnin.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@ToString
@NoArgsConstructor
//@XmlRootElement(name = "product")
//@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractEntity implements Serializable {

    @NotNull
    @Size(min = 3, max = 10, message = "Название должно быть от 3 до 10 символов")
    @Column(nullable = false)
    private String name = "";

    @NotNull
    @DecimalMax(value= "99999.9", message = "Цена не должна превышать 99999.9")
    @DecimalMin(value = "0.1", message = "Цена не должна быть меньше 0.1")
    @Column(nullable = false)
    private Double price = 0d;

    @Nullable
    @Size(min = 10, max = 100, message = "Описание должно быть от 10 до 100 символов")
    @Column
    private String description;

    public Product(@NotNull String name, @NotNull Double price) {
        this.name = name;
        this.price = price;
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Tag> tags;

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

}
