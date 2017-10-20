package com.capgemini.wolimierz.cost.model;

import com.capgemini.wolimierz.cost.CostSettingsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "COST_SETTINGS")
@NoArgsConstructor
@AllArgsConstructor
public class CostSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCOMMODATION_PRICE", nullable = false)
    private double accommodationPrice;

    @Column(name = "MEAL_PRICE", nullable = false)
    private double mealPrice;

    @Column(name = "TRAINING_PRICE", nullable = false)
    private double trainingPrice;

    @Column(name = "MARGIN")
    private double margin;

    @Column(name = "DISCOUNT")
    private double discount;

    public void updateFrom(CostSettingsDto costSettingsDto) {
        this.mealPrice = costSettingsDto.getMealPrice();
        this.accommodationPrice = costSettingsDto.getAccommodationPrice();
        this.trainingPrice = costSettingsDto.getTrainingPrice();
        this.margin = costSettingsDto.getMargin();
        this.discount = costSettingsDto.getDiscount();
    }
}
