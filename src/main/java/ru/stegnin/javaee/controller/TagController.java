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
import java.util.Collection;

@Named
@RequestScoped
@URLMapping(id = "tag", pattern = "/tags", viewId = "WEB-INF/faces/tag.xhtml")
public class TagController implements Serializable {
    @Inject
    private TagRepository tagRepo;

    public Collection<Tag> getTags() {
        return tagRepo.findAll();
    }

    private Tag tag;

    public void delete() {
        tagRepo.delete(tag);
    }

    @NotNull
    public Tag getTag() {
        return tag;
    }

    public void setTag(@NotNull final String tagId) {
        this.tag = tagRepo.findOne(tagId);
    }

    public void attrListener(ActionEvent event){
        String tagId = (String) event.getComponent().getAttributes().get("tagId");
        setTag(tagId);
    }
}
