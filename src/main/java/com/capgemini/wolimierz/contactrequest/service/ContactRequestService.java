package com.capgemini.wolimierz.contactrequest.service;

import com.capgemini.wolimierz.contactrequest.ContactRequestDto;

import java.util.List;
import java.util.UUID;

public interface ContactRequestService {
    List<ContactRequestDto> findAll(boolean findOnlyNotRead);

    ContactRequestDto find(UUID globalId);

    long countPending();

    ContactRequestDto create(ContactRequestDto contactRequestDto);
}
