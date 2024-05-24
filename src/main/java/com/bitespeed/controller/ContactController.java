package com.bitespeed.controller;

import com.bitespeed.dto.ContactResponse;
import com.bitespeed.model.Contact;
import com.bitespeed.servcie.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {


    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping("/identify")
    public ResponseEntity<ContactResponse> getIdentifyData(@RequestBody Contact contactReq) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContactResponse(contactReq));
    }
    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.saveContact(contact));
    }
    @GetMapping("/get_own_details")
    public ResponseEntity<List<Contact>> getContacts(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContacts(contact));
    }
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getAllContacts());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id, @RequestBody Contact contactDetails) {
        Contact contact = contactService.getContactUpdate(id,contactDetails);
            return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        contactService.getContactById(id);
        return ResponseEntity.noContent().build();
    }
}
