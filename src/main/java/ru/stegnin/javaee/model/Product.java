package ru.stegnin.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Named;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Named
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractModel implements Serializable {

    @NotNull
    @Size(min = 3, max = 10, message = "Название должно быть от 3 до 10 символов")
    private String name = "";

    @NotNull
    @DecimalMax(value= "99999.9", message = "Цена не должна превышать 99999.9")
    @DecimalMin(value = "0.1", message = "Цена не должна быть меньше 0.1")
    private Double price = 0d;

    @Nullable
    @Size(min = 10, max = 100, message = "Описание должно быть от 10 до 100 символов")
    private String description;

    public Product(@NotNull String name, @NotNull Double price) {
        this.name = name;
        this.price = price;
    }

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
