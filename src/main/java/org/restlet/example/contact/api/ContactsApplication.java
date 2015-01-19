package org.restlet.example.contact.api;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.example.contact.api.db.Db;
import org.restlet.example.contact.api.resource.AppReadmeResource;
import org.restlet.example.contact.api.resource.CompaniesResource;
import org.restlet.example.contact.api.resource.CompanyResource;
import org.restlet.example.contact.api.resource.ContactResource;
import org.restlet.example.contact.api.resource.ContactsResource;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
import org.restlet.routing.Router;

import java.util.logging.Logger;

public class ContactsApplication extends Application {

    public static Logger logger = Engine.getLogger(ContactsApplication.class);

    /**
     * Starts the web application
     */
    public static void main(String[] args) throws Exception {
        logger.info("Contacts application starting...");

        //create data
        Db.populate();

        //launch server
        Component component = new Component();
        component.setName("contact application");
        component.getServers().add(Protocol.HTTP, 8000);
        component.getDefaultHost().attach(new ContactsApplication());
        component.start();

        logger.info("Contacts application started on port 8000");
        logger.info("URL: http://localhost:8000/");
    }

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
