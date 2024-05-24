package com.bitespeed.servcie;

import com.bitespeed.dao.ContactRepository;
import com.bitespeed.dto.ContactResponse;
import com.bitespeed.model.Contact;
import com.bitespeed.model.LinkPrecedence;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
 // for identify api
    public ContactResponse getContactResponse(Contact contactReq) {
        List<Contact> contacts=contactRepository.findByPhoneNumberOrEmail(contactReq.getPhoneNumber(),contactReq.getEmail());
        if (contacts.isEmpty()){
            contactReq.setCreatedAt(LocalDateTime.now());
            contactReq.setUpdatedAt(LocalDateTime.now());// just for fill data
            contactReq.setLinkPrecedence(LinkPrecedence.PRIMARY);
            Contact contact=contactRepository.save(contactReq);
            contacts.add(contact);
        }
        List<Contact> sortedContacts = contacts.stream()
                .sorted(Comparator.comparing(contact -> contact.getLinkPrecedence() == LinkPrecedence.PRIMARY ? 0 : 1))
                .collect(Collectors.toList());

        List<String> emails = sortedContacts.stream()
                .map(Contact::getEmail)
                .collect(Collectors.toList());

        List<String> phoneNumbers = sortedContacts.stream()
                .map(Contact::getPhoneNumber)
                .collect(Collectors.toList());

        List<Integer> contactIds = sortedContacts.stream()
                .filter(contactl -> contactl.getLinkPrecedence() == LinkPrecedence.SECONDARY)
                .map(Contact::getId)
                .collect(Collectors.toList());

        int primaryContactId = sortedContacts.isEmpty() ? 0 : sortedContacts.get(0).getId();

        return ContactResponse.builder()
                .primaryContactId(primaryContactId)
                .emails(emails)
                .phoneNumbers(phoneNumbers)
                .secondaryContactIds(contactIds)
                .build();
    }
    public Contact saveContact(Contact contact) {
        List<Contact> contactList= contactRepository.
                findByPhoneNumberOrEmail(contact.getPhoneNumber(),contact.getEmail());
          contact.setCreatedAt(LocalDateTime.now());
          contact.setUpdatedAt(LocalDateTime.now());// just for fill data
          if(contactList.isEmpty())
           {
               contact.setLinkPrecedence(LinkPrecedence.PRIMARY);
               return contactRepository.save(contact);
           }
           contact.setLinkedId(contactList.stream().map(Contact::getId).findFirst().orElse(null));
           contact.setLinkPrecedence(LinkPrecedence.SECONDARY);
        return contactRepository.save(contact);
    }
    public List<Contact> getContacts(Contact contact) {
        return contactRepository.findByPhoneNumberOrEmail(contact.getPhoneNumber(),contact.getEmail());
    }
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


    public Contact getContactUpdate(Integer id, Contact contactDetails) {
        Optional<Contact> existingContactOptional = contactRepository.findById(id);
        if (existingContactOptional.isPresent()) {
            Contact existingContact = existingContactOptional.get();
            existingContact.setPhoneNumber(contactDetails.getPhoneNumber());
            existingContact.setEmail(contactDetails.getEmail());
            existingContact.setLinkedId(contactDetails.getLinkedId());
            existingContact.setLinkPrecedence(contactDetails.getLinkPrecedence());
            existingContact.setUpdatedAt(LocalDateTime.now());
            existingContact.setDeletedAt(null); // Assuming you need to clear the deletedAt field
            return contactRepository.save(existingContact);
        } else {

            throw new NullPointerException("Contact not found with id: " + id);
        }
    }

    public void getContactById(Integer id) {
        Optional<Contact> existingContactOptional = contactRepository.findById(id);
        if (existingContactOptional.isPresent()) {
          contactRepository.delete(existingContactOptional.get());
        }
        throw new NullPointerException("");
    }


}
