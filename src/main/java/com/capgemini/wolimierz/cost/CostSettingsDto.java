package com.capgemini.wolimierz.cost;

import com.capgemini.wolimierz.cost.model.CostSettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
public class CostSettingsDto {
    private double accommodationPrice;
    private double mealPrice;
    private double trainingPrice;

    private Double margin;
    private Double discount;
    @Setter
    private List<DetailedCost> seasonCosts;
    @Setter
    private List<DetailedCost> eventTypeCosts;
    @Setter
    private List<DetailedCost> eventSizeCosts;

    public CostSettingsDto(double accommodationPrice, double mealPrice, double trainingPrice, Double margin, Double discount) {
        this.accommodationPrice = accommodationPrice;
        this.mealPrice = mealPrice;
        this.trainingPrice = trainingPrice;
        this.margin = margin;
        this.discount = discount;
    }

    public static CostSettingsDto from(CostSettings costSettings) {
        return new CostSettingsDto(costSettings.getAccommodationPrice(), costSettings.getMealPrice(),
                costSettings.getTrainingPrice(), costSettings.getMargin(), costSettings.getDiscount());
    }

    public static CostSettingsDto forClientFrom(CostSettings costSettings) {
        return new CostSettingsDto(costSettings.getAccommodationPrice(), costSettings.getMealPrice(),
                costSettings.getTrainingPrice(), null, null);
    }
}
