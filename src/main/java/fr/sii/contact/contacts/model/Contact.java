package fr.sii.contact.contacts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "contacts")
public class Contact {
    @Id
    private UUID id;
    private String name, firstname, service;
    private Date date;

    public Contact(String name, String firstname, String service, Date date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.firstname = firstname;
        this.service = service;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
