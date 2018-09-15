package ru.stegnin.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Tag extends AbstractModel {
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
