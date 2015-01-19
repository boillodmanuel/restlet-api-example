package org.restlet.example.contact.api.resource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.db.CompanyDb;
import org.restlet.example.contact.api.model.Company;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class CompanyResource extends ServerResource {

    private Integer companyId;

    public CompanyResource() {
        setName("Company");
        setName("Company resource");
    }

    @Override
    protected void doInit() throws ResourceException {
        companyId = ResourceUtils.getPathVariableAsInteger(this, "companyId");
    }


    @ApiOperation(value = "get a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the company"),
            @ApiResponse(code = 404, message = "company not found", response = NotFoundException.class),
    })
    @Get
    public Company getCompany() throws NotFoundException {
        Company company = CompanyDb.INSTANCE.getCompany(companyId);
        if (company == null) {
            throw new NotFoundException("No company with id '" + companyId + "'");
        }
        return company;
    }

    @ApiOperation(value = "update a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the updated company"),
            @ApiResponse(code = 404, message = "company not found", response = NotFoundException.class),
            @ApiResponse(code = 422, message = "company not valid", response = BadEntityException.class)
    })
    @Put
    public Company updateCompany(Company company) throws NotFoundException, BadEntityException {
        ResourceUtils.notNull(company);
        company.validate();
        return CompanyDb.INSTANCE.updateCompany(companyId, company);
    }

    @ApiOperation(value = "delete a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 204, message = "company deleted"),
            @ApiResponse(code = 404, message = "company not found", response = NotFoundException.class),
    })
    @Delete
    public void deleteCompany() throws NotFoundException {
        boolean removed = CompanyDb.INSTANCE.deleteCompany(companyId);
        if (!removed) {
            throw new NotFoundException("No company with id '" + companyId + "'");
        }
    }
}
