package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.cost.service.CostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
@RequestMapping(path = "/cost")
public class CostController {
    private final CostSettingService costSettingService;

    @Autowired
    public CostController(CostSettingService costSettingService) {
        this.costSettingService = costSettingService;
    }
}
