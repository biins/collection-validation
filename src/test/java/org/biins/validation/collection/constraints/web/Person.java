package org.biins.validation.collection.constraints.web;

import org.biins.validation.collection.constraints.ElementsSize;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Martin Janys
 */
public class Person {

    @ElementsSize(element = String.class, value = @Size(max = 2))
    private List<String> tags;

    @Size(max = 2)
    private String name;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
