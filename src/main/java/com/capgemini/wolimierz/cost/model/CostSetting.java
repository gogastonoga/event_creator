package com.capgemini.wolimierz.cost.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "COST_SETTINGS")
@NoArgsConstructor
@AllArgsConstructor
public class CostSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCOMMODATION_PRICE", nullable = false)
    private Double accommodationPrice;

    @Column(name = "MEAL_PRICE", nullable = false)
    private Double mealPrice;

    @Column(name = "TRAINING_PRICE", nullable = false)
    private Double trainingPrice;

    @Column(name = "MARGIN")
    private double margin;

    @Column(name = "DISCOUNT")
    private double discount;
}
