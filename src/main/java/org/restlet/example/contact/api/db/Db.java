package org.restlet.example.contact.api.db;

import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.model.Contact;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author Manuel Boillod
 */
public class Db {

    public static void populate() throws Exception {
        Company companyRestlet = new Company();
        companyRestlet.setName("Restlet");
        companyRestlet.setTags(Arrays.asList("API", "open source"));
        CompanyDb.INSTANCE.addCompany(companyRestlet);

        Company companyGoogle = new Company();
        companyGoogle.setName("Google");
        companyGoogle.setTags(Arrays.asList("search", "cloud"));
        CompanyDb.INSTANCE.addCompany(companyGoogle);

        Contact contact = new Contact();
        contact.setFirstName("Manuel");
        contact.setLastName("Boillod");
        contact.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse("29/08/1981"));
        contact.setCompanyId(companyRestlet.getId());
        ContactDb.INSTANCE.addContact(contact);
    }
}
