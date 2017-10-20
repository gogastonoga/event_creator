package com.capgemini.wolimierz.event.repository;

import com.capgemini.wolimierz.event.model.EventSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventSizeRepository extends JpaRepository<EventSize, Long> {
    EventSize findByGlobalId(UUID eventSizeId);

    List<EventSize> findAllByGlobalIdIn(List<UUID> eventSizeIds);
}
