package ru.stegnin.javaee.support;

public class Constants {
    public static final String API = "/api";
    public static final String KEY_GENERATOR = "secretKey";

//    API для работы с User
    public static final String API_USERS = "/users";
    public static final String API_USER_ID = "userId";
    public static final String API_USERS_USER_ID = "/{userId}";
    public static final String API_USERS_AUTH = "/auth";

//    API для работы с Product
    public static final String API_PRODUCTS = "/products";
    public static final String API_PRODUCT_ID = "productId";
    public static final String API_PRODUCTS_PRODUCT_ID =  "/{productId}";
    public static final String API_PRODUCTS_TAG = "/{tagId}";

//    MESSAGES FOR RESPONSE
    public static final String MESSAGE_LOGIN_SUCCESSFUL = "User log in successful!";
    public static final String MESSAGE_LOGIN_UNAUTHORIZED = "User not found!";

//    API для работы с тэгами
    public static final String API_TAGS = "/tags";
    public static final String API_TAG_ID = "tagId";
    public static final String API_TAGS_TAG_ID = "/{tagId}";

}
