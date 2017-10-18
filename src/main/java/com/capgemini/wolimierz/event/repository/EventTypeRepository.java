package com.capgemini.wolimierz.event.repository;

import com.capgemini.wolimierz.event.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    List<EventType> findAllByGlobalIdIn(List<UUID> eventTypeIds);
}
