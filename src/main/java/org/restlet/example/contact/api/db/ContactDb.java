package org.restlet.example.contact.api.db;

import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.model.Contact;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Manuel Boillod
 */
public class ContactDb {

    public static ContactDb INSTANCE = new ContactDb();

    private AtomicInteger idGenerator = new AtomicInteger();

    private Map<Integer, Contact> contacts = new LinkedHashMap<>();

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts.values());
    }

    public Contact getContact(Integer contactId) {
        return contacts.get(contactId);
    }

    public Contact addContact(Contact contact) {
        if (contact.getId() == null) {
            contact.setId(idGenerator.incrementAndGet());
        } else {
            Contact existing = getContact(contact.getId());
            if (existing != null) {
                throw new BadParameterException("Contact with id '" + contact.getId() + "' already exists");
            }
        }
        contacts.put(contact.getId(), contact);
        return contact;
    }

    public Contact updateContact(Integer contactId, Contact contact) {
        Contact existing = getContact(contactId);
        if (existing == null) {
            throw new NotFoundException("No contact with id '" + contactId + "'");
        }
        contacts.put(contactId, contact);
        return contact;
    }

    public boolean deleteContact(Integer contactId) {
        Contact existing = contacts.remove(contactId);
        return existing != null;
    }
}
