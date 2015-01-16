package org.restlet.example.contact.api.resource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.db.CompanyDb;
import org.restlet.example.contact.api.model.Company;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.List;

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
    public List<Company> getCompanies() {
        return CompanyDb.INSTANCE.getCompanies();
    }

    @ApiOperation(value = "add a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added company"),
            @ApiResponse(code = 404, message = "company not found", response = NotFoundException.class),
            @ApiResponse(code = 422, message = "company not valid", response = BadEntityException.class)
    })
    @Post
    public Company addCompany(Company company) throws NotFoundException, BadEntityException {
        ResourceUtils.notNull(company);
        company.validate();
        return CompanyDb.INSTANCE.addCompany(company);
    }
}
