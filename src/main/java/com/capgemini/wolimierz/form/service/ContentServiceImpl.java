package com.capgemini.wolimierz.form.service;

import com.capgemini.wolimierz.EntityDtoWithGlobalId;
import com.capgemini.wolimierz.UpdatableEntity;
import com.capgemini.wolimierz.controller.dto.ContentDto;
import com.capgemini.wolimierz.controller.dto.EventSizeDto;
import com.capgemini.wolimierz.controller.dto.EventTypeDto;
import com.capgemini.wolimierz.controller.dto.HomePageDto;
import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.event.model.Season;
import com.capgemini.wolimierz.event.predefined.PredefinedEventType;
import com.capgemini.wolimierz.event.predefined.PredefinedSize;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import com.capgemini.wolimierz.form.Form;
import com.capgemini.wolimierz.form.FormDto;
import com.capgemini.wolimierz.form.HomePageSettings;
import com.capgemini.wolimierz.form.repository.FormRepository;
import com.capgemini.wolimierz.form.repository.HomePageSettingsRepository;
import com.capgemini.wolimierz.utils.EnvironmentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {
    private final FormRepository formRepository;
    private final HomePageSettingsRepository homePageSettingsRepository;
    private final SeasonRepository seasonRepository;
    private final EventSizeRepository eventSizeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EnvironmentService environmentService;

    //https://www.percederberg.net/tools/text_converter.html
    @Value("${initial.budget.description}")
    private String budgetDescription;
    @Value("${initial.additional.description}")
    private String additionalDescription;
    @Value("${initial.participants.description}")
    private String participantsDescription;
    @Value("${initial.accommodation.description}")
    private String accommodationDescription;
    @Value("${initial.dateform.description}")
    private String dateFormDescription;
    @Value("${initial.error.description}")
    private String errorDescription;
    @Value("${initial.summary.description}")
    private String summaryDescription;
    @Value("${initial.homepage.description}")
    private String homePageDescription;
    @Value("${initial.season.summer.name}")
    private String summerSeasonName;
    @Value("${initial.season.winter.name}")
    private String winterSeasonName;
    @Value("${initial.season.winter.from}")
    private String winterFrom;
    @Value("${initial.season.winter.to}")
    private String winterTo;
    @Value("${initial.season.summer.from}")
    private String summerFrom;
    @Value("${initial.season.summer.to}")
    private String summerTo;

    @Getter
    @Value("${initial.admin.mail}")
    private String adminEmail;
    @Getter
    @Value("${initial.admin.password}")
    private String adminPassword;
    @Getter
    @Value("${initial.admin.name}")
    private String adminName;
    @Getter
    @Value("${initial.admin.surname}")
    private String adminSurname;

    @Autowired
    public ContentServiceImpl(FormRepository formRepository,
                              HomePageSettingsRepository homePageSettingsRepository,
                              SeasonRepository seasonRepository, EventSizeRepository eventSizeRepository,
                              EventTypeRepository eventTypeRepository, EnvironmentService environmentService) {
        this.eventSizeRepository = eventSizeRepository;
        this.formRepository = formRepository;
        this.homePageSettingsRepository = homePageSettingsRepository;
        this.seasonRepository = seasonRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.environmentService = environmentService;
    }

    @PostConstruct
    public void initContentSettings() {
        initFormSettings();
        initHomePageSettings();
        initSeasons();
        initEventSizes();
        initEventTypes();
    }

    private void initEventTypes() {
        if (eventTypeRepository.findAll().isEmpty()) {
            eventTypeRepository.save(Arrays.stream(PredefinedEventType.values())
                    .map(type -> new EventType(type.getTranslation(), type.getDescription(), 1))
                    .collect(Collectors.toList()));
        }
    }

    private void initEventSizes() {
        if (eventSizeRepository.findAll().isEmpty()) {
            eventSizeRepository.save(Arrays.stream(PredefinedSize.values())
                    .map(EventSize::new)
                    .collect(Collectors.toList())
            );
        }
    }

    private void initSeasons() {
        if (seasonRepository.findAll().isEmpty()) {
            seasonRepository.save(
                    Arrays.asList(
                            new Season(null, summerSeasonName, getLocalDateFromIsoString(summerFrom),
                                    getLocalDateFromIsoString(summerTo), UUID.randomUUID(), 20),
                            new Season(null, winterSeasonName, getLocalDateFromIsoString(winterFrom),
                                    getLocalDateFromIsoString(winterTo), UUID.randomUUID(), 0))
            );
        }
    }

    private void initHomePageSettings() {
        if (homePageSettingsRepository.findAll().isEmpty()) {
            homePageSettingsRepository.save(new HomePageSettings(null, homePageDescription, null, null, null)
            );
        }
    }

    private void initFormSettings() {
        if (formRepository.findAll().isEmpty()) {
            formRepository.save(new Form(null,
                            budgetDescription,
                            additionalDescription,
                            participantsDescription,
                            accommodationDescription,
                            dateFormDescription,
                            summaryDescription,
                            errorDescription
                    )
            );
        }
    }

    private LocalDate getLocalDateFromIsoString(String summerFrom) {
        return LocalDate.parse(summerFrom, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public ContentDto getContent() {
        String mediaUrl = environmentService.getMediaBasicUrl();
        return new ContentDto(homePageSettingsRepository.findAll().stream().findAny().orElse(null),
                formRepository.findAll().stream().findAny().orElse(null), eventTypeRepository.findAll(),
                eventSizeRepository.findAll(), seasonRepository.findAll(), mediaUrl);
    }

    @Override
    public HomePageDto updateHomePage(HomePageDto homePageDto) {
        HomePageSettings homePageSettings = homePageSettingsRepository.findAll().stream().findAny()
                .orElseThrow(IllegalStateException::new);
        homePageSettings.setDescription(homePageDto.getDescription());
        String mediaUrl = environmentService.getMediaBasicUrl();
        return HomePageDto.from(homePageSettingsRepository.save(homePageSettings), mediaUrl);
    }

    @Override
    public FormDto updateForm(FormDto formDto) {
        Form form = formRepository.findAll().stream().findAny()
                .orElseThrow(IllegalStateException::new);
        form.uptateFrom(formDto);
        String mediaUrl = environmentService.getMediaBasicUrl();
        return FormDto.from(formRepository.save(form),
                eventTypeRepository.findAll(),
                eventSizeRepository.findAll(),
                seasonRepository.findAll(),
                mediaUrl);
    }

    @Override
    public List<SeasonDto> updateSeasons(List<SeasonDto> seasons) {
        List<UUID> seasonIds = collectGlobalIds(seasons);
        List<Season> seasonsToUpdate = seasonRepository.findAllByGlobalIdIn(seasonIds);
        update(seasons, seasonsToUpdate);
        return seasonRepository.save(seasonsToUpdate).stream()
                .map(SeasonDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventTypeDto> updateEventTypes(List<EventTypeDto> eventTypes) {
        List<UUID> eventTypeIds = collectGlobalIds(eventTypes);
        List<EventType> eventTypesToUpdate = eventTypeRepository.findAllByGlobalIdIn(eventTypeIds);
        update(eventTypes, eventTypesToUpdate);
        eventTypesToUpdate.forEach(eventType -> updateEntity(eventTypes, eventType));
        return eventTypeRepository.save(eventTypesToUpdate).stream()
                .map(EventTypeDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventSizeDto> updateEventSizes(List<EventSizeDto> eventSizes) {
        List<UUID> eventTypeIds = collectGlobalIds(eventSizes);
        List<EventSize> eventSizesToUpdate = eventSizeRepository.findAllByGlobalIdIn(eventTypeIds);
        update(eventSizes, eventSizesToUpdate);
        String mediaUrl = environmentService.getMediaBasicUrl();
        return eventSizeRepository.save(eventSizesToUpdate).stream()
                .map(eventSize -> EventSizeDto.from(eventSize, mediaUrl))
                .collect(Collectors.toList());
    }

    private <T extends EntityDtoWithGlobalId> void update(List<T> dtos, List<? extends UpdatableEntity<T>> entities) {
        entities.forEach(eventSize -> updateEntity(dtos, eventSize));
    }

    private <T extends EntityDtoWithGlobalId> void updateEntity(List<T> dtos, UpdatableEntity<T> entity) {
        Optional<T> dto = dtos.stream()
                .filter(eventSizeDto -> eventSizeDto.getGlobalId().equals(entity.getGlobalId()))
                .findAny();
        dto.ifPresent(entity::updateFrom);
    }

    private <T extends EntityDtoWithGlobalId> List<UUID> collectGlobalIds(List<T> dtos) {
        return dtos == null ? Collections.emptyList() : dtos.stream().map(T::getGlobalId).collect(Collectors.toList());
    }
}
