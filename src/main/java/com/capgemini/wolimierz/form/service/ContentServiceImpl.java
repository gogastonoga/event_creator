package com.capgemini.wolimierz.form.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final EnvironmentService environmentService;

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
        if (homePageSettingsRepository.findAll().isEmpty()) {
            homePageSettingsRepository.save(new HomePageSettings(null, homePageDescription, null, null, null)
            );
        }
        if (seasonRepository.findAll().isEmpty()) {
            seasonRepository.save(
                    Arrays.asList(
                            new Season(null, summerSeasonName, getLocalDateFromIsoString(summerFrom),
                                    getLocalDateFromIsoString(summerTo), UUID.randomUUID(), 20),
                            new Season(null, winterSeasonName, getLocalDateFromIsoString(winterFrom),
                                    getLocalDateFromIsoString(winterTo), UUID.randomUUID(), 0))
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
                    .map(type -> new EventType(type.getTranslation(), type.getDescription(), 1))
                    .collect(Collectors.toList()));
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
        List<UUID> seasonIds = seasons.stream().map(SeasonDto::getGlobalId).collect(Collectors.toList());
        List<Season> seasonsToUpdate = seasonRepository.findAllByGlobalIdIn(seasonIds);
        seasonsToUpdate.
                forEach(season -> {
                    Optional<SeasonDto> updateFrom = seasons.stream()
                            .filter(s -> s.getGlobalId().equals(season.getGlobalId())).findAny();
                    updateFrom.ifPresent(season::updateFrom);
                });
        return seasonRepository.save(seasonsToUpdate).stream()
                .map(season -> new SeasonDto(season.getFromDate(), season.getToDate(),
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
        String mediaUrl = environmentService.getMediaBasicUrl();
        return eventSizeRepository.save(eventSizesToUpdate).stream()
                .map(eventSize -> new EventSizeDto(eventSize.getDescription(),
                        eventSize.getGlobalId(), eventSize.getImage() == null ?
                        null : mediaUrl + eventSize.getImage().getGlobalId()))
                .collect(Collectors.toList());
    }
}
