package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.TagRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
@URLMapping(id = "tag-add", pattern = "/tag-add", viewId = "/WEB-INF/faces/tag-add.xhtml")
public class TagAddController implements Serializable {
    @Inject
    private TagRepository tagRepo;

    @NotNull
    private Tag tag = new Tag();

    @NotNull
    public Tag getTag() {
        return tag;
    }

    public void setTag(@NotNull final Tag tag) {
        tag.setName(tag.getName());
        this.tag = tag;
    }

    @NotNull
    public String save() {
        tagRepo.save(tag);
        return "tags";
    }
}
