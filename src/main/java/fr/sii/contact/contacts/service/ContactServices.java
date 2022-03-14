package fr.sii.contact.contacts.service;

import fr.sii.contact.contacts.model.Contact;
import fr.sii.contact.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactServices {

    @Autowired
    private ContactRepository contactRepository;

    public void addContact(Contact contact) {
        contactRepository.insert(contact);
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getOne(UUID id) {
        return contactRepository.findById(id);
    }

    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(UUID id) {
        contactRepository.deleteById(id);
    }
}
