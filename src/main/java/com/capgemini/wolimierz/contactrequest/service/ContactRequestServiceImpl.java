package com.capgemini.wolimierz.contactrequest.service;

import com.capgemini.wolimierz.contactrequest.ContactRequestDto;
import com.capgemini.wolimierz.contactrequest.ContactRequestRepository;
import com.capgemini.wolimierz.contactrequest.model.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactRequestServiceImpl implements ContactRequestService {
    private final ContactRequestRepository contactRequestRepository;

    @Autowired
    public ContactRequestServiceImpl(ContactRequestRepository contactRequestRepository) {
        this.contactRequestRepository = contactRequestRepository;
    }

    @Override
    public List<ContactRequestDto> findAll(boolean findOnlyNotRead) {
        List<ContactRequest> contactRequests = findOnlyNotRead ?
                contactRequestRepository.findAllByWasReadFalse() : contactRequestRepository.findAll();
        return contactRequests.stream().map(ContactRequestDto::from).collect(Collectors.toList());
    }

    @Override
    public ContactRequestDto find(UUID globalId) {
        ContactRequest contactRequest = contactRequestRepository.findByGlobalId(globalId);
        if (contactRequest == null) {
            return null;
        } else {
            if (!contactRequest.getWasRead()) {
                contactRequest.setWasRead();
                contactRequestRepository.save(contactRequest);
            }
            return ContactRequestDto.from(contactRequest);
        }
    }

    @Override
    public long countPending() {
        return contactRequestRepository.countByWasReadFalse();
    }

    @Override
    public ContactRequestDto create(ContactRequestDto contactRequestDto) {
        return ContactRequestDto
                .from(contactRequestRepository.save(
                        new ContactRequest(contactRequestDto.getMessage(), contactRequestDto.getCreatorMail()))
                );
    }
}
