package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.*;
import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.form.FormDto;
import com.capgemini.wolimierz.form.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
@RequestMapping(path = "/wolimierz/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ContentDto getContent() {
        return contentService.getContent();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/home", method = RequestMethod.PUT)
    public HomePageDto updateHomePage(@RequestBody HomePageDto homePageDto) {
        return contentService.updateHomePage(homePageDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/form", method = RequestMethod.PUT)
    public FormDto updateForm(@RequestBody FormDto formDto) {
        return contentService.updateForm(formDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/seasons", method = RequestMethod.PUT)
    public List<SeasonDto> updateSeasons(@RequestBody SeasonsContainer seasonsContainer) {
        return contentService.updateSeasons(seasonsContainer.getSeasons());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/eventtypes", method = RequestMethod.PUT)
    public List<EventTypeDto> updateEventTypes(@RequestBody EventTypesContainer eventTypesContainer) {
        return contentService.updateEventTypes(eventTypesContainer.getEventTypes());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/eventsizes", method = RequestMethod.PUT)
    public List<EventSizeDto> updateEventSizes(@RequestBody EventSizesContainer eventSizesContainer) {
        return contentService.updateEventSizes(eventSizesContainer.getEventSizes());
    }
}
