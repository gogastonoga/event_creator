package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.cost.service.CostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RequestMapping(path = "/wolimierz/costsettings")
@RestController
public class CostSettingController {
    private final CostSettingService costSettingService;

    @Autowired
    public CostSettingController(CostSettingService costSettingService) {
        this.costSettingService = costSettingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CostSettingsDto getCostSettings() {
        return costSettingService.getCostSettings();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CostSettingsDto updateCostSettings(CostSettingsDto costSettingsDto) {
        return costSettingService.updateCostSettings(costSettingsDto);
    }
}
