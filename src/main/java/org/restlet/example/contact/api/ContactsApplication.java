package org.restlet.example.contact.api;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.example.contact.api.db.Db;
import org.restlet.example.contact.api.resource.RootResource;
import org.restlet.example.contact.api.resource.CompaniesResource;
import org.restlet.example.contact.api.resource.CompanyResource;
import org.restlet.example.contact.api.resource.ContactResource;
import org.restlet.example.contact.api.resource.ContactsResource;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;

import java.util.logging.Logger;

public class ContactsApplication extends Application {

    public static Logger logger = Engine.getLogger(ContactsApplication.class);

    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_USER = "user";

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

        Router publicRouter = publicResources();
        ChallengeAuthenticator apiGuard = createApiGuard();
        Router privateRouter = privateResources();

        //chain restlet components
        publicRouter.attachDefault(apiGuard);
        apiGuard.setNext(privateRouter);

        return publicRouter;
    }

    public Router publicResources() {
        Router router = new Router();

        router.attach("/", RootResource.class);

        //Attach Swagger Specifications
        attachSwaggerSpecification1(router);
        attachSwaggerSpecification2(router);
        return router;
    }

    public Router privateResources() {
        Router router = new Router();

        //Attach Resources
        router.attach("/contacts", ContactsResource.class);
        router.attach("/contacts/{contactId}", ContactResource.class);

        router.attach("/companies", CompaniesResource.class);
        router.attach("/companies/{companyId}", CompanyResource.class);
        return router;
    }

    private ChallengeAuthenticator createApiGuard() {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
                getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        //Create in-memory users and roles.
        MemoryRealm realm = new MemoryRealm();

        User admin = new User("admin", "password");
        realm.getUsers().add(admin);
        realm.map(admin, Role.get(this, ROLE_ADMIN));
        realm.map(admin, Role.get(this, ROLE_USER));

        User user = new User("user", "password");
        realm.getUsers().add(user);
        realm.map(user, Role.get(this, ROLE_USER));

        // Attach - Verifier : to check authentication - Enroler : to check
        // authorization (roles)
        apiGuard.setVerifier(realm.getVerifier());
        apiGuard.setEnroler(realm.getEnroler());

        // You can also create your own authentication/authorization system by
        // creating classes extending SecretVerifier or LocalVerifier (for
        // authentication) and Enroler (for authorization) and set these to the
        // guard.
        return apiGuard;
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
