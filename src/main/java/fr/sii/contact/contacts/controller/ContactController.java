package fr.sii.contact.contacts.controller;

import fr.sii.contact.contacts.model.Contact;
import fr.sii.contact.contacts.service.ContactServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://127.0.0.1/")

public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final ContactServices contactServices;

    public ContactController(ContactServices contactServices) {
        this.contactServices = contactServices;
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> all() {
        return new ResponseEntity<>(contactServices.getAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/newContact")
    public ResponseEntity<Contact> newContact(@RequestBody Contact contact) {
        if (contact == null
                || !isValid(contact.getFirstname())
                || !isValid(contact.getName())
                || !isValid(contact.getService())
                || contact.getDate() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("created: " + contact.getId().toString());
        contactServices.addContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.ACCEPTED);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> one(@PathVariable UUID id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("getting: " + id);
        Optional<Contact> contact = contactServices.getOne(id);
        return contact.map(value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updateContact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        if (contact == null
                || !isValid(contact.getFirstname())
                || !isValid(contact.getName())
                || !isValid(contact.getService())
                || contact.getDate() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("updated: " + contact.getId().toString());
        Optional<Contact> ocontact = contactServices.getOne(contact.getId());
        return ocontact.map(value -> {
            contactServices.updateContact(contact);
            return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/removeContact/{id}")
    public ResponseEntity<Boolean> deleteContact(@PathVariable String id) {
        if (id == null || id.equals("")) return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        logger.info("deleted: " + id);
        UUID uuid = UUID.fromString(id);
        Optional<Contact> contact = contactServices.getOne(uuid);
        return contact.map(value -> {
            contactServices.deleteContact(uuid);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }).orElseGet(() -> new ResponseEntity<>(false, HttpStatus.NOT_FOUND));
    }

    private boolean isValid(String s) {
        return (s != null && !s.isEmpty());
    }

}
