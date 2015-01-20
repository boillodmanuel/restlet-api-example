package org.restlet.example.contact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.restlet.example.contact.api.core.validation.ValidationErrors;

import java.util.List;

/**
 * @author Manuel Boillod
 */
@JsonRootName("Company")
public class Company {

    private Integer id;

    private String name;

    private List<String> tags;

    @ApiModelProperty("the company id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("the company name")
    @JsonProperty(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("the company tags")
    public List<String> getTags() {
        return tags;
    }

    @JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void validate() {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id == null) {
            validationErrors.addFieldError("id", "This field is required");
        }
        if (Strings.isNullOrEmpty(name)) {
            validationErrors.addFieldError("name", "This field is required");
        }
        validationErrors.checkErrors("Company entity is not valid");
    }

}
