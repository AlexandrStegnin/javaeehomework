package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@URLMapping(id = "product-edit", pattern = "/product-edit", viewId = "/WEB-INF/faces/product-edit.xhtml")
public class ProductEditController implements Serializable {
    @Inject
    private ProductRepository productRepo;

    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull String productId) {
        this.product = productRepo.findOne(productId);
    }

    @NotNull
    public String update(Product product) {
        productRepo.update(product);
        return "catalog";
    }

    public void attrListener(ActionEvent event){
        String productId = (String) event.getComponent().getAttributes().get("productId");
        product = productRepo.findOne(productId);
    }
}
