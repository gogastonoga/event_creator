package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingRepository;
import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.cost.model.CostSettings;
import com.capgemini.wolimierz.event.model.KindOfDays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
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
            costSettingRepository.save(new CostSettings(null, 50.0, 20.0, 100.0, 0, 0));
        }
    }

    @Override
    public CostSettingsDto getCostSettings() {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        return costSettings == null ? null : CostSettingsDto.from(costSettings);
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
        return CostSettingsDto.from(costSettingRepository.save(costSettings));
    }

    @Override
    public Double calculateCost(int nights, int rooms, int usersNumber, KindOfDays kindOfDays) {
        CostSettings costSettings = costSettingRepository.findAll().get(0);
        Double estimatedCost = getAccommodationCost(nights, rooms, costSettings) + getMealsCost(nights, usersNumber, costSettings) + costSettings.getTrainingPrice();
        return (estimatedCost * (1 + costSettings.getMargin() / 100)) * (1 - costSettings.getDiscount() / 100);
    }

    private double getMealsCost(int nights, int usersNumber, CostSettings costSettings) {
        return nights * usersNumber * costSettings.getMealPrice();
    }

    private double getAccommodationCost(int nights, int rooms, CostSettings costSettings) {
        return nights * rooms * costSettings.getAccommodationPrice();
    }
}
