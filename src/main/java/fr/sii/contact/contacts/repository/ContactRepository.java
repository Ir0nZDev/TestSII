package fr.sii.contact.contacts.repository;

import fr.sii.contact.contacts.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ContactRepository extends MongoRepository<Contact, UUID> {

}
