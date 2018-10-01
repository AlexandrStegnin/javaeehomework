package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.TagRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@URLMapping(id = "tag-edit", pattern = "/tag-edit", viewId = "/WEB-INF/faces/tag-edit.xhtml")
public class TagEditController implements Serializable {
    @Inject
    private TagRepository tagRepo;

    private Tag tag = new Tag();

    public Tag getTag() {
        return tag;
    }

    public void setTag(@NotNull String tagId) {
        this.tag = tagRepo.findOne(tagId);
    }

    @NotNull
    public String update(Tag tag) {
        tagRepo.update(tag);
        return "tags";
    }

    public void attrListener(ActionEvent event){
        String tagId = (String) event.getComponent().getAttributes().get("tagId");
        tag = tagRepo.findOne(tagId);
    }

    public void delete() {
        tagRepo.delete(tag);
    }
}
