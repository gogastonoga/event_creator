package com.capgemini.wolimierz.form.service;

import com.capgemini.wolimierz.controller.dto.ContentDto;
import com.capgemini.wolimierz.controller.dto.EventSizeDto;
import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import com.capgemini.wolimierz.controller.dto.HomePageDto;
import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.form.FormDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService {
    ContentDto getContent();

    HomePageDto updateHomePage(HomePageDto homePageDto);

    FormDto updateForm(FormDto formDto);

    List<SeasonDto> updateSeasons(List<SeasonDto> seasons);

    List<EventTypeDto> updateEventTypes(List<EventTypeDto> eventTypes);

    List<EventSizeDto> updateEventSizes(List<EventSizeDto> eventSizes);

}
