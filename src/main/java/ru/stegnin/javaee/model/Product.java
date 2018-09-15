package ru.stegnin.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends AbstractModel {

    @NonNull
    private String name;

    @NonNull
    private double price;

    @Nullable
    private String description;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
