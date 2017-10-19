package com.capgemini.wolimierz.form.service;

import com.capgemini.wolimierz.controller.dto.ContentDto;
import com.capgemini.wolimierz.controller.dto.EventSizeDto;
import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import com.capgemini.wolimierz.controller.dto.HomePageDto;
import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.event.predefined.PredefinedEventType;
import com.capgemini.wolimierz.event.predefined.PredefinedSize;
import com.capgemini.wolimierz.event.model.Season;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import com.capgemini.wolimierz.form.Form;
import com.capgemini.wolimierz.form.FormDto;
import com.capgemini.wolimierz.form.HomePageSettings;
import com.capgemini.wolimierz.form.repository.FormRepository;
import com.capgemini.wolimierz.form.repository.HomePageSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {
    private final FormRepository formRepository;
    private final HomePageSettingsRepository homePageSettingsRepository;
    private final SeasonRepository seasonRepository;
    private final EventSizeRepository eventSizeRepository;
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public ContentServiceImpl(FormRepository formRepository,
                              HomePageSettingsRepository homePageSettingsRepository,
                              SeasonRepository seasonRepository, EventSizeRepository eventSizeRepository,
                              EventTypeRepository eventTypeRepository) {
        this.eventSizeRepository = eventSizeRepository;
        this.formRepository = formRepository;
        this.homePageSettingsRepository = homePageSettingsRepository;
        this.seasonRepository = seasonRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @PostConstruct
    public void initContentSettings() {
        if (formRepository.findAll().isEmpty()) {
            formRepository.save(new Form(null,
                            "Budget descirption",
                            "Additional form part description",
                            "Participants form part description",
                            "Accommodation form part description",
                            "Date form description"
                    )
            );
        }
        if (homePageSettingsRepository.findAll().isEmpty()) {
            homePageSettingsRepository.save(new HomePageSettings(null, "Home page descirption", null, null)
            );
        }
        if (seasonRepository.findAll().isEmpty()) {
            seasonRepository.save(
                    Arrays.asList(
                            new Season(null, "Lato", LocalDate.of(2017, 7, 1),
                                    LocalDate.of(2017, 8, 30), "Description1",
                                    UUID.randomUUID()),
                            new Season(null, "Cium", LocalDate.of(2017, 7, 1),
                                    LocalDate.of(2017, 8, 30), "Description2",
                                    UUID.randomUUID()))
            );
        }
        if (eventSizeRepository.findAll().isEmpty()) {
            eventSizeRepository.save(Arrays.stream(PredefinedSize.values())
                    .map(EventSize::new)
                    .collect(Collectors.toList())
            );
        }
        if (eventTypeRepository.findAll().isEmpty()) {
            eventTypeRepository.save(Arrays.stream(PredefinedEventType.values())
                    .map(type -> new EventType(type.getTranslation(), type.getDescription()))
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public ContentDto getContent() {
        return new ContentDto(homePageSettingsRepository.findAll().stream().findAny().orElse(null),
                formRepository.findAll().stream().findAny().orElse(null), eventTypeRepository.findAll(),
                eventSizeRepository.findAll(), seasonRepository.findAll());
    }

    @Override
    public HomePageDto updateHomePage(HomePageDto homePageDto) {
        HomePageSettings homePageSettings = homePageSettingsRepository.findAll().stream().findAny()
                .orElseThrow(IllegalStateException::new);
        homePageSettings.setDescription(homePageDto.getDescription());
        return HomePageDto.from(homePageSettingsRepository.save(homePageSettings));
    }

    @Override
    public FormDto updateForm(FormDto formDto) {
        Form form = formRepository.findAll().stream().findAny()
                .orElseThrow(IllegalStateException::new);
        form.uptateFrom(formDto);
        return FormDto.from(formRepository.save(form),
                eventTypeRepository.findAll(),
                eventSizeRepository.findAll(),
                seasonRepository.findAll());
    }

    @Override
    public List<SeasonDto> updateSeasons(List<SeasonDto> seasons) {
        List<UUID> seasonIds = seasons.stream().map(SeasonDto::getGlobalId).collect(Collectors.toList());
        List<Season> seasonsToUpdate = seasonRepository.findAllByGlobalIdIn(seasonIds);
        seasonsToUpdate.
                forEach(season -> {
                    Optional<SeasonDto> updateFrom = seasons.stream()
                            .filter(s -> s.getGlobalId().equals(season.getGlobalId())).findAny();
                    updateFrom.ifPresent(season::updateFrom);
                });
        return seasonRepository.save(seasonsToUpdate).stream()
                .map(season -> new SeasonDto(season.getFromDate(), season.getToDate(), season.getDescription(),
                        season.getName(), season.getGlobalId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventTypeDto> updateEventTypes(List<EventTypeDto> eventTypes) {
        List<UUID> eventTypeIds = eventTypes.stream().map(EventTypeDto::getGlobalId).collect(Collectors.toList());
        List<EventType> eventTypesToUpdate = eventTypeRepository.findAllByGlobalIdIn(eventTypeIds);
        eventTypesToUpdate.forEach(eventType ->
                {
                    Optional<EventTypeDto> dto = eventTypes.stream()
                            .filter(eventTypeDto -> eventTypeDto.getGlobalId().equals(eventType.getGlobalId()))
                            .findAny();
                    dto.ifPresent(eventType::updateFrom);
                }
        );
        return eventTypeRepository.save(eventTypesToUpdate).stream()
                .map(eventType -> new EventTypeDto(eventType.getDescription(), eventType.getTranslation(), eventType.getGlobalId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventSizeDto> updateEventSizes(List<EventSizeDto> eventSizes) {
        List<UUID> eventTypeIds = eventSizes.stream().map(EventSizeDto::getGlobalId).collect(Collectors.toList());
        List<EventSize> eventSizesToUpdate = eventSizeRepository.findAllByGlobalIdIn(eventTypeIds);
        eventSizesToUpdate.forEach(eventSize ->
                {
                    Optional<EventSizeDto> dto = eventSizes.stream()
                            .filter(eventSizeDto -> eventSizeDto.getGlobalId().equals(eventSize.getGlobalId()))
                            .findAny();
                    dto.ifPresent(eventSize::updateFrom);
                }
        );
        return eventSizeRepository.save(eventSizesToUpdate).stream()
                .map(eventSize -> new EventSizeDto(eventSize.getDescription(),
                        eventSize.getGlobalId(), eventSize.getImage() == null ? null : eventSize.getImage().getGlobalId()))
                .collect(Collectors.toList());
    }
}
