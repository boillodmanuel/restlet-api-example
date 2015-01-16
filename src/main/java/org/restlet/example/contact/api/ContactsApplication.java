package org.restlet.example.contact.api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.example.contact.api.resource.AppReadmeResource;
import org.restlet.example.contact.api.resource.CompaniesResource;
import org.restlet.example.contact.api.resource.CompanyResource;
import org.restlet.example.contact.api.resource.ContactResource;
import org.restlet.example.contact.api.resource.ContactsResource;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
import org.restlet.routing.Router;

public class ContactsApplication extends Application {

    public ContactsApplication() {
        setName("Contacts API application");
        setDescription("Full description of the Contacts API");
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router();

        //Attach Swagger Specifications
        attachSwaggerSpecification1(router);
        attachSwaggerSpecification2(router);

        //Attach Resources
        router.attach("/contacts", ContactsResource.class);
        router.attach("/contacts/{contactId}", ContactResource.class);

        router.attach("/companies", CompaniesResource.class);
        router.attach("/companies/{companyId}", CompanyResource.class);

        router.attach("/app/readme", AppReadmeResource.class);

        return router;
    }

    private void attachSwaggerSpecification1(Router router) {
        SwaggerSpecificationRestlet swaggerSpecificationRestlet = new SwaggerSpecificationRestlet(this);
        swaggerSpecificationRestlet.setBasePath("http://myapp.com/api/");
        swaggerSpecificationRestlet.attach(router);
    }

    private void attachSwaggerSpecification2(Router router) {
        Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(this);
        swagger2SpecificationRestlet.setBasePath("http://myapp.com/api/");
        swagger2SpecificationRestlet.attach(router);
    }
}
