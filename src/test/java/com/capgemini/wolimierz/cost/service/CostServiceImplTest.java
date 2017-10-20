package com.capgemini.wolimierz.cost.service;

import com.capgemini.wolimierz.cost.CostSettingRepository;
import com.capgemini.wolimierz.cost.model.CostSettings;
import com.capgemini.wolimierz.event.model.KindOfDays;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.event.repository.EventTypeRepository;
import com.capgemini.wolimierz.event.repository.SeasonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CostServiceImplTest {
    @Mock
    CostSettingRepository costSettingRepository;
    @Mock
    EventTypeRepository eventTypeRepository;
    @Mock
    EventSizeRepository eventSizeRepository;
    @Mock
    SeasonRepository seasonRepository;
    @InjectMocks
    CostSettingServiceImpl costSettingService;

    @Test
    public void calculateCostTestWithSingleNight() {
        //given
        when(costSettingRepository.findAll()).thenReturn(Collections.singletonList(new CostSettings(null, 15, 20, 100, 0, 0)));
        //when
        double cost = costSettingService.calculateCost(1, 1, 1, KindOfDays.WEEKEND);
        //then
        assertEquals(135.0, cost, 0);
    }

    @Test
    public void calculateCostTestWithMultipleNight() {
        //given
        when(costSettingRepository.findAll()).thenReturn(Collections.singletonList(new CostSettings(null, 15, 20, 100, 0, 0)));
        //when
        double cost = costSettingService.calculateCost(2, 1, 3, KindOfDays.WEEKEND);
        //then
        assertEquals(30 + 120 + 200, cost, 0);
    }

    @Test
    public void calculateCostTestWithMultipleNightWithMargin() {
        //given
        when(costSettingRepository.findAll()).thenReturn(Collections.singletonList(new CostSettings(null, 15, 20, 100, 10, 0)));
        //when
        double cost = costSettingService.calculateCost(2, 1, 3, KindOfDays.WEEKEND);
        //then
        assertEquals((30 + 120 + 200) * 1.1, cost, 0);
    }

    @Test
    public void calculateCostTestWithMultipleNightWithMarginAndDiscount() {
        //given
        when(costSettingRepository.findAll()).thenReturn(Collections.singletonList(new CostSettings(null, 15, 20, 100, 10, 10)));
        //when
        double cost = costSettingService.calculateCost(2, 1, 3, KindOfDays.WEEKEND);
        //then
        assertEquals((30 + 120 + 200) * 1.1 * 0.9, cost, 0);
    }

    @Test
    public void calculateCostTestWithMultipleNightWithDiscount() {
        //given
        when(costSettingRepository.findAll()).thenReturn(Collections.singletonList(new CostSettings(null, 15, 20, 100, 10, 10)));
        //when
        double cost = costSettingService.calculateCost(2, 1, 3, KindOfDays.WEEKEND);
        //then
        assertEquals((30 + 120 + 200) * 0.9, cost, 0);
    }
}
