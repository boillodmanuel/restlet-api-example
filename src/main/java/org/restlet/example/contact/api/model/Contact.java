package org.restlet.example.contact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.restlet.example.contact.api.core.validation.ValidationErrors;

import java.util.Date;

public class Contact {

    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthday;

    private Integer companyId;

    @ApiModelProperty("the contact id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("the contact first name")
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty("the contact last name")
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty("the contact birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ApiModelProperty("the contact company")
    @JsonProperty("company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void validate() {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id == null) {
            validationErrors.addFieldError("id", "This field is required");
        }
        if (Strings.isNullOrEmpty(firstName)) {
            validationErrors.addFieldError("first_name", "This field is required");
        }
        if (Strings.isNullOrEmpty(lastName)) {
            validationErrors.addFieldError("last_name", "This field is required");
        }
        validationErrors.checkErrors("Contact entity is not valid");
    }
}
