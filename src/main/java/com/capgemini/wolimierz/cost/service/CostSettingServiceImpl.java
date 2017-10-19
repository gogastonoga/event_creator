package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingRepository;
import com.capgemini.wolimierz.cost.model.CostSetting;
import com.capgemini.wolimierz.event.model.KindOfDays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CostSettingServiceImpl implements CostSettingService {
    private final CostSettingRepository costSettingRepository;

    @Autowired
    public CostSettingServiceImpl(CostSettingRepository costSettingRepository) {
        this.costSettingRepository = costSettingRepository;
    }

    @PostConstruct
    public void init() {
        if (costSettingRepository.findAll().isEmpty()) {
            costSettingRepository.save(new CostSetting(null, 50.0, 20.0, 100.0, 0, 0));
        }
    }

    @Override
    public Double getCostEstimation(int nights, int rooms, int usersNumber, KindOfDays kindOfDays) {
        CostSetting costSetting = costSettingRepository.findAll().get(0);
        Double estimatedCost = getAccommodationCost(nights, rooms, costSetting) + getMealsCost(nights, usersNumber, costSetting) + costSetting.getTrainingPrice();
        return (estimatedCost * (1 + costSetting.getMargin() / 100)) * (1 - costSetting.getDiscount() / 100);
    }

    private double getMealsCost(int nights, int usersNumber, CostSetting costSetting) {
        return nights * usersNumber * costSetting.getMealPrice();
    }

    private double getAccommodationCost(int nights, int rooms, CostSetting costSetting) {
        return nights * rooms * costSetting.getAccommodationPrice();
    }
}
