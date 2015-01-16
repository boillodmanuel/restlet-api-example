package org.restlet.example.contact.api;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import java.util.logging.Logger;

/**
 * @author Manuel Boillod
 */
public class StartContactsApplication {

    public static Logger logger = Engine.getLogger(StartContactsApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("Contacts application starting...");

        Component component = new Component();
        component.setName("contact application");
        component.getServers().add(Protocol.HTTP, 8000);
        component.getDefaultHost().attach(new ContactsApplication());
        component.start();

        logger.info("Contacts application started on port 8000");

    }
}
