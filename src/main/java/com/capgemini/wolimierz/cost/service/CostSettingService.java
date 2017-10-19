package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.event.model.KindOfDays;

public interface CostSettingService {
    CostSettingsDto getCostSettings();

    CostSettingsDto updateCostSettings(CostSettingsDto costSettingsDto);

    Double calculateCost(int nights, int rooms, int usersNumber, KindOfDays kindOfDays);
}
