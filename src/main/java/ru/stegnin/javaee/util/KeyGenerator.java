package ru.stegnin.javaee.util;

import java.security.Key;

public interface KeyGenerator {
    Key generateKey(String keyString);
}
