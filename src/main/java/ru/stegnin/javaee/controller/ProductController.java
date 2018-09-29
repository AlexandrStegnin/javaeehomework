package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
@URLMapping(id = "product-add", pattern = "/product-add", viewId = "/WEB-INF/faces/product-add.xhtml")
public class ProductController implements Serializable {
    @Inject
    private ProductRepository productRepo;

    @NotNull
    private Product product = new Product();

    @NotNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull final Product product) {
        this.product = product;
    }

    @NotNull
    public String save() {
        productRepo.save(product);
        return "catalog";
    }
}
