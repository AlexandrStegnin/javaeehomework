package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.primefaces.event.RowEditEvent;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.TagRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
@URLMapping(id = "tag", pattern = "/tags", viewId = "WEB-INF/faces/tag.xhtml")
public class TagController implements Serializable {
    @Inject
    private TagRepository tagRepo;

    public Collection<Tag> getTags() {
        return tagRepo.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Тэг отредактирован", ((Tag) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Редактирование отменено", ((Tag) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TagRepository getTagRepo() {
        return tagRepo;
    }

    public void setTagRepo(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

}
