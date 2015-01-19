package org.restlet.example.contact.api.resource;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.db.ContactDb;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ContactResource extends ServerResource {

    private Integer contactId;

    public ContactResource() {
        setName("Contact");
        setName("Contact resource");
    }

    @Override
    protected void doInit() throws ResourceException {
        contactId = ResourceUtils.getPathVariableAsInteger(this, "contactId");
    }

    @ApiOperation(value = "get a contact", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the contact"),
            @ApiResponse(code = 404, message = "contact not found", response = NotFoundException.class),
    })
    @Get
    public Contact getContact() throws NotFoundException {
        ResourceUtils.checkRole(this, ContactsApplication.ROLE_USER);
        Contact contact = ContactDb.INSTANCE.getContact(contactId);
        if (contact == null) {
            throw new NotFoundException("No contact with id '" + contactId + "'");
        }
        return contact;
    }

    @ApiOperation(value = "update a contact", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the updated contact"),
            @ApiResponse(code = 404, message = "contact not found", response = NotFoundException.class),
            @ApiResponse(code = 422, message = "contact not valid", response = BadEntityException.class)
    })
    @Put
    public Contact updateContact(Contact contact) throws NotFoundException, BadEntityException {
        ResourceUtils.checkRole(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(contact);
        contact.validate();
        return ContactDb.INSTANCE.updateContact(contactId, contact);
    }

    @ApiOperation(value = "delete a contact", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 204, message = "contact deleted"),
            @ApiResponse(code = 404, message = "contact not found", response = NotFoundException.class),
    })
    @Delete
    public void deleteContact() throws NotFoundException {
        ResourceUtils.checkRole(this, ContactsApplication.ROLE_USER, ContactsApplication.ROLE_ADMIN);
        boolean removed = ContactDb.INSTANCE.deleteContact(contactId);
        if (!removed) {
            throw new NotFoundException("No contact with id '" + contactId + "'");
        }
    }
}
