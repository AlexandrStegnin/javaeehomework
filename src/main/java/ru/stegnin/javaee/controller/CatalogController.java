package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import org.primefaces.event.RowEditEvent;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
@URLMapping(id = "catalog", pattern = "/catalog", viewId = "/WEB-INF/faces/catalog.xhtml")
public class CatalogController implements Serializable {
    @Inject
    private ProductRepository productRepo;

    public Collection<Product> getProducts() {
        return productRepo.findAll();
    }

    private Product product;

    public void onRowEdit(RowEditEvent event) {
        Product toUpdate = (Product) event.getObject();
        FacesMessage msg = new FacesMessage("Товар отредактирован", (toUpdate.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete() {
        productRepo.delete(product);
    }

    @NotNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull final String productId) {
        this.product = productRepo.findOne(productId);
    }

    public void attrListener(ActionEvent event){
        String productId = (String) event.getComponent().getAttributes().get("productId");
        setProduct(productId);
    }
}
