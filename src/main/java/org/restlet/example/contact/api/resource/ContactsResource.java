package org.restlet.example.contact.api.resource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.db.ContactDb;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ContactsResource extends ServerResource {

    public ContactsResource() {
        setName("Contacts");
        setDescription("Contact list resource");
    }

    @ApiOperation(value = "list the contacts", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the list of contacts"),
    })
    @Get
    public List<Contact> getContacts() {
        return ContactDb.INSTANCE.getContacts();
    }

    @ApiOperation(value = "add a contact", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added contact"),
            @ApiResponse(code = 404, message = "contact not found", response = NotFoundException.class),
            @ApiResponse(code = 422, message = "contact not valid", response = BadEntityException.class)
    })
    @Post
    public Contact addContact(Contact contact) throws NotFoundException, BadEntityException {
        ResourceUtils.notNull(contact);
        contact.validate();
        return ContactDb.INSTANCE.addContact(contact);
    }
}
