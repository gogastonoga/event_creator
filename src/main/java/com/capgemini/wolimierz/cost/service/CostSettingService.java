package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.event.model.KindOfDays;

public interface CostSettingService {
    Double getCostEstimation(int nights, int rooms, int usersNumber, KindOfDays kindOfDays);
}
