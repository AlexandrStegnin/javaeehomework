package ru.stegnin.javaee.support;

public class Constants {
    public static final String DISPATCHER_PREFIX = "WEB-INF/views/";
    public static final String PRODUCTS = "products";
    public static final String PRODUCT = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String CART = "cart";
    public static final String COUNT = "count";
    public static final String REST = "/rest";
    public static final String API = "/api";

//    API для работы с User
    public static final String API_USERS = "/users";
    public static final String API_USER_ID = "userId";
    public static final String API_USERS_USER_ID = API_USERS + "/{userId}";

//    API для работы с Product
    public static final String API_PRODUCTS = "/products";
    public static final String API_PRODUCT_ID = "productId";
    public static final String API_PRODUCTS_PRODUCT_ID = API_PRODUCTS + "/{productId}";
}
