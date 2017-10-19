package com.capgemini.wolimierz.event.repository;

import com.capgemini.wolimierz.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByGlobalId(UUID globalId);
}
