package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.primefaces.event.RowEditEvent;
import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.service.ProductService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
@URLMapping(id = "catalog", pattern = "/catalog", viewId = "/WEB-INF/faces/catalog.xhtml")
public class CatalogController implements Serializable {
    @Inject
    private ProductService productRepository;

    public Collection<Product> getProducts() {
        return productRepository.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Товар отредактирован", ((Product) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Редактирование отменено", ((Product) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductService productRepository) {
        this.productRepository = productRepository;
    }

}
