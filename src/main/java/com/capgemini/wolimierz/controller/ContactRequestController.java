package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.contactrequest.ContactRequestDto;
import com.capgemini.wolimierz.contactrequest.service.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RequestMapping(path = "/wolimierz/contactrequests")
@RestController
public class ContactRequestController {
    private final ContactRequestService contactRequestService;

    @Autowired
    public ContactRequestController(ContactRequestService contactRequestService) {
        this.contactRequestService = contactRequestService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET, params = "id")
    public ContactRequestDto find(@RequestParam(name = "id") UUID id) {
        return contactRequestService.find(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET)
    public List<ContactRequestDto> findAll() {
        return contactRequestService.findAll(false);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET, params = "read=false")
    public List<ContactRequestDto> findAllNotRead() {
        return contactRequestService.findAll(true);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    @RequestMapping(method = RequestMethod.GET, params = "action=count_pending")
    public long countPending() {
        return contactRequestService.countPending();
    }
}
