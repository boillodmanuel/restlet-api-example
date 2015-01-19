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
        Company company = new Company();
        company.setName("Restlet");
        company.setTags(Arrays.asList("API", "open source"));
        CompanyDb.INSTANCE.addCompany(company);

        Contact contact = new Contact();
        contact.setFirstName("Manuel");
        contact.setLastName("Boillod");
        contact.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse("29/08/1981"));
        contact.setCompanyId(company.getId());
        ContactDb.INSTANCE.addContact(contact);
    }
}
