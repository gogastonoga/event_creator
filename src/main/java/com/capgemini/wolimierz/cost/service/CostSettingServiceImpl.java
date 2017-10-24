package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingRepository;
import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.cost.DetailedCost;
import com.capgemini.wolimierz.cost.model.CostSettings;
import com.capgemini.wolimierz.event.model.*;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@Service
public class CostSettingServiceImpl implements CostSettingService {
    private final CostSettingRepository costSettingRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EventSizeRepository eventSizeRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public CostSettingServiceImpl(CostSettingRepository costSettingRepository, EventTypeRepository eventTypeRepository,
                                  EventSizeRepository eventSizeRepository, SeasonRepository seasonRepository) {
        this.costSettingRepository = costSettingRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.eventSizeRepository = eventSizeRepository;
        this.seasonRepository = seasonRepository;
    }

    @PostConstruct
    public void init() {
        if (costSettingRepository.findAll().isEmpty()) {
            costSettingRepository.save(new CostSettings(null, 50.0, 20.0, 100.0, 0, 0));
        }
    }

    @Override
    public CostSettingsDto getCostSettings() {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        CostSettingsDto dto = costSettings == null ? null : CostSettingsDto.from(costSettings);
        addDetailedCosts(dto);
        return dto;
    }

    private void addDetailedCosts(CostSettingsDto dto) {
        addEventTypeDetailedCosts(dto);
        addEventSizeDetailedCosts(dto);
        addSeasonDetailedCosts(dto);
    }

    private void addSeasonDetailedCosts(CostSettingsDto dto) {
        List<Season> seasons = seasonRepository.findAll();
        dto.setSeasonCosts(mapToDetailedCost(seasons));
    }

    private void addEventSizeDetailedCosts(CostSettingsDto dto) {
        List<EventSize> eventSizes = eventSizeRepository.findAll();
        dto.setEventSizeCosts(mapToDetailedCost(eventSizes));
    }

    private void addEventTypeDetailedCosts(CostSettingsDto dto) {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        dto.setEventTypeCosts(mapToDetailedCost(eventTypes));
    }

    private List<DetailedCost> mapToDetailedCost(List<? extends Cost> costs) {
        return costs.stream()
                .map(cost -> new DetailedCost(cost.getGlobalId(), cost.getName(), cost.getCostFactor()))
                .collect(Collectors.toList());
    }

    @Override
    public CostSettingsDto updateCostSettings(CostSettingsDto costSettingsDto) {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        if (costSettings != null) {
            costSettings.updateFrom(costSettingsDto);
        } else {
            costSettings = new CostSettings(null, costSettingsDto.getAccommodationPrice(),
                    costSettingsDto.getMealPrice(), costSettingsDto.getTrainingPrice(), costSettingsDto.getMargin(),
                    costSettingsDto.getDiscount());
        }
        updateDetailedCosts(costSettingsDto);
        return CostSettingsDto.from(costSettingRepository.save(costSettings));
    }

    private void updateDetailedCosts(CostSettingsDto costSettingsDto) {
        updateDetailedEventTypeCosts(costSettingsDto.getEventTypeCosts());
        updateDetailedEventSizeCosts(costSettingsDto.getEventSizeCosts());
        updateDetailedSeasonsCosts(costSettingsDto.getSeasonCosts());
    }

    private void updateDetailedEventTypeCosts(List<DetailedCost> updates) {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        updateEventTypeDetailedCost(eventTypes, updates);
        eventTypeRepository.save(eventTypes);
    }

    private void updateDetailedEventSizeCosts(List<DetailedCost> updates) {
        List<EventSize> eventSizes = eventSizeRepository.findAll();
        updateEventTypeDetailedCost(eventSizes, updates);
        eventSizeRepository.save(eventSizes);
    }

    private void updateDetailedSeasonsCosts(List<DetailedCost> updates) {
        List<Season> seasons = seasonRepository.findAll();
        updateEventTypeDetailedCost(seasons, updates);
        seasonRepository.save(seasons);
    }

    private void updateEventTypeDetailedCost(List<? extends Cost> costs, List<DetailedCost> eventTypeCosts) {
        eventTypeCosts.forEach(update -> costs.stream()
                .filter(cost -> cost.getGlobalId().equals(update.getGlobalId()))
                .findAny().ifPresent(cost -> cost.setCostFactor(update.getPrice())));
    }

    @Override
    public Double calculateCost(int nights, int rooms, int usersNumber, KindOfDays kindOfDays) {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        Double estimatedCost = getAccommodationCost(nights, rooms, costSettings, 1) +
                getMealsCost(nights, usersNumber, costSettings, 1) +
                nights * costSettings.getTrainingPrice();
        return (estimatedCost * (1 + costSettings.getMargin() / 100)) * (1 - costSettings.getDiscount() / 100);
    }

    @Override
    public Double calculateDetailedCost(int nights, int rooms, int guestsNumber, KindOfDays kindOfDays, List<EventType> eventTypes, EventSize eventSize, Season season) {
        CostSettings costSettings = costSettingRepository.findAll().get(0);//todo nigts+1? for trainings  // size facotr?!?!  // wykluczanie się liczby dni/sezony/typu dni etc.
        // nights * guestNumbers * accomodation *factor -> seasons/ type of days?!?!?TODO dzwoń !!
        Double estimatedCost = getAccommodationCost(nights, rooms, costSettings, 1 + season.getCostFactor() / 100) +
                getMealsCost(nights, guestsNumber, costSettings, 1 + eventSize.getCostFactor() / 100) +
                nights * eventTypes.stream().mapToDouble(EventType::getCostFactor).sum();
        return (estimatedCost * (1 + costSettings.getMargin() / 100)) * (1 - costSettings.getDiscount() / 100);
    }/**
     ACCOMODATION:
     nights * guestNumbers * accomodation *factor
     guestNumbers -> guestNumbers || eventSize cost?!
     factor-> season || typeOfDays?!
     MEALS
     guestNumbers -> guestNumbers || eventSize cost?!
     number of meals? -> calculation  is the number of days defined by user
     EVENT_TYPE
     impact of number of people / seasons on cost
     multiple types -> sum / max of many / other?!?!?!
     order of applying of margin and discount -> it impact on "force" of the later operation
 **/

    @Override
    public CostSettingsDto getClientCostSettings() {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        CostSettingsDto dto = CostSettingsDto.forClientFrom(costSettings);
        addDetailedCosts(dto);
        return dto;
    }

    private double getMealsCost(int nights, int usersNumber, CostSettings costSettings, double sizeFactor) {
        return nights * usersNumber * costSettings.getMealPrice() * sizeFactor;
    }

    private double getAccommodationCost(int nights, int rooms, CostSettings costSettings, double seasonFactor) {
        return nights * rooms * costSettings.getAccommodationPrice() * seasonFactor;
    }
}
