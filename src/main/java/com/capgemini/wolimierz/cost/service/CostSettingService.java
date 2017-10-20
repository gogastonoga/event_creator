package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.model.EventType;
import com.capgemini.wolimierz.event.model.KindOfDays;
import com.capgemini.wolimierz.event.model.Season;

import java.util.List;

public interface CostSettingService {
    CostSettingsDto getCostSettings();

    CostSettingsDto updateCostSettings(CostSettingsDto costSettingsDto);

    Double calculateCost(int nights, int rooms, int usersNumber, KindOfDays kindOfDays);

    CostSettingsDto getClientCostSettings();

    Double calculateDetailedCost(int nights, int rooms, int guestsNumber, KindOfDays kindOfDays, List<EventType> eventTypes, EventSize eventSize, Season season);
}
