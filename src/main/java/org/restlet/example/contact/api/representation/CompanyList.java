package org.restlet.example.contact.api.representation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.restlet.example.contact.api.model.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Boillod
 */
@JacksonXmlRootElement(localName = "companies")
public class CompanyList {

    List<Company> companies = new ArrayList<>();

    public CompanyList() {
    }

    public CompanyList(List<Company> companies) {
        this.companies = companies;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "company")
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
