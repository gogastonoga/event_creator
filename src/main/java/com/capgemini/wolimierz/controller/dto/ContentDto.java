package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.event.model.Season;
import com.capgemini.wolimierz.form.Form;
import com.capgemini.wolimierz.form.FormDto;
import com.capgemini.wolimierz.form.HomePageSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContentDto {
    private HomePageDto mainPage;
    private FormDto formDto;

    public ContentDto(HomePageSettings homePageSettings, Form form, List<EventType> eventTypes,
                      List<EventSize> eventSizes, List<Season> seasons) {
        this.mainPage = HomePageDto.from(homePageSettings);
        this.formDto = FormDto.from(form, eventTypes, eventSizes, seasons);
    }
}
