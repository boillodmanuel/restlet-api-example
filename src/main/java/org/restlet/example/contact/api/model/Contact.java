package org.restlet.example.contact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.restlet.example.contact.api.core.validation.ValidationErrors;

public class Contact {

    private Integer id;

    private String firstName;

    private String lastName;

    private Company company;

    @ApiModelProperty("the contact id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("the contact firstName")
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty("the contact lastName")
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty("the contact company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void validate() {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id == null) {
            validationErrors.addFieldError("id", "This field is required");
        }
        if (Strings.isNullOrEmpty(firstName)) {
            validationErrors.addFieldError("firstName", "This field is required");
        }
        if (Strings.isNullOrEmpty(lastName)) {
            validationErrors.addFieldError("lastName", "This field is required");
        }
        validationErrors.checkErrors("Contact entity is not valid");
    }
}
