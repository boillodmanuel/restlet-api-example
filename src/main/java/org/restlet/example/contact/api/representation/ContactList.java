package org.restlet.example.contact.api.representation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.restlet.example.contact.api.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * This representation is required for xml variant as describe in
 * <a href="https://github.com/FasterXML/jackson-dataformat-xml#known-limitations">jackson documentation </a>
 *
 * @author Manuel Boillod
 */
@JacksonXmlRootElement(localName = "contacts")
public class ContactList {

    List<Contact> contacts = new ArrayList<>();

    public ContactList() {
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "contact")
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
