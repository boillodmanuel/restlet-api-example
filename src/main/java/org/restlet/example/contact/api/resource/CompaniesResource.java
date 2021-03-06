package org.restlet.example.contact.api.resource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.db.CompanyDb;
import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.representation.CompanyList;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class CompaniesResource extends ServerResource {

    public CompaniesResource() {
        setName("Companies");
        setDescription("Company list resource");
    }

    @ApiOperation(value = "list the companies", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the list of companies"),
    })
    @Get
    public CompanyList getCompanies() {
        ResourceUtils.checkRole(this, ContactsApplication.ROLE_USER);
        return new CompanyList(CompanyDb.INSTANCE.getCompanies());
    }

    @ApiOperation(value = "add a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added company"),
            @ApiResponse(code = 422, message = "company not valid", response = BadEntityException.class)
    })
    @Post
    public Company addCompany(Company company) throws BadEntityException {
        ResourceUtils.checkRole(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(company);
        company.validate();
        return CompanyDb.INSTANCE.addCompany(company);
    }
}
