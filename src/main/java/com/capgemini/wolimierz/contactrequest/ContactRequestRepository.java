package com.capgemini.wolimierz.contactrequest;

import com.capgemini.wolimierz.contactrequest.model.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRequestRepository extends JpaRepository<ContactRequest, Long> {
    List<ContactRequest> findAllByWasReadFalse();

    long countByWasReadFalse();

    ContactRequest findByGlobalId(UUID globalId);
}
