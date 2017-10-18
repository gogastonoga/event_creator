package com.capgemini.wolimierz.event.service;

import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EventSizeServiceImpl implements EventSizeService {
    private final EventSizeRepository eventSizeRepository;

    @Autowired
    public EventSizeServiceImpl(EventSizeRepository eventSizeRepository) {
        this.eventSizeRepository = eventSizeRepository;
    }

    @PostConstruct
    public void initEventSizes() {
        /*if (eventSizeRepository.findAll().isEmpty()) {
            eventSizeRepository.save(Arrays.stream(PredefinedSize.values())
                    .map(EventSize::new)
                    .collect(Collectors.toList())
            );
        }*/
    }
}
