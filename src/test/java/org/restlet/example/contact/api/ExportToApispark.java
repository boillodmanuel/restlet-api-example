package org.restlet.example.contact.api;

import org.restlet.ext.apispark.Introspector;

/**
 * @author Manuel Boillod
 */
public class ExportToApispark {

    /**
     * Export the web API as a Descriptor cell to APISpark
     */
    public static void main(String[] args) throws Exception {
        exportWebApi_asDescriptor_toApiSpark();
    }

    /**
     * Print the Introspector help
     */
    public static void printHelp() throws Exception {
        Introspector.main(new String[]{
                "--help",
        });
    }

    /**
     * Export the web API as a Descriptor cell to APISpark
     */
    public static void exportWebApi_asDescriptor_toApiSpark() throws Exception {
        Introspector.main(new String[]{
                "-S", "http://v2.apispark.com",
                //TODO Enter your username (from your APISpark account)
                "-u", "YOUR USERNAME",
                //TODO Enter your secret key (from your APISpark account)
                "-p", "YOUR SECRET KEY",
                "--create-descriptor",
                "org.restlet.example.contact.api.ContactsApplication"});
    }
}
