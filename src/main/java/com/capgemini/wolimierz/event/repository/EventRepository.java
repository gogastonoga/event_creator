package com.capgemini.wolimierz.event.repository;

import com.capgemini.wolimierz.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
