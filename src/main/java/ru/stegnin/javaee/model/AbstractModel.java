package ru.stegnin.javaee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class AbstractModel {
    @NonNull private String id = UUID.randomUUID().toString();
}
