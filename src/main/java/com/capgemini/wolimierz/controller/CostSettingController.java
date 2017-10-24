package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.cost.CostSettingsDto;
import com.capgemini.wolimierz.cost.service.CostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RequestMapping(path = "/wolimierz/costsettings")
@RestController
public class CostSettingController {
    private final CostSettingService costSettingService;

    @Autowired
    public CostSettingController(CostSettingService costSettingService) {
        this.costSettingService = costSettingService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public CostSettingsDto getCostSettings() {
        return costSettingService.getCostSettings();
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public CostSettingsDto getClientCostSettings() {
        return costSettingService.getClientCostSettings();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public CostSettingsDto updateCostSettings(@RequestBody @Valid CostSettingsDto costSettingsDto) {
        return costSettingService.updateCostSettings(costSettingsDto);
    }
}
