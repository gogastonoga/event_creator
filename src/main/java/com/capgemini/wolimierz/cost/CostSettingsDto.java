package com.capgemini.wolimierz.cost;

import com.capgemini.wolimierz.cost.model.CostSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CostSettingsDto {
    private double accommodationPrice;
    private double mealPrice;
    private double trainingPrice;
    private Double margin;
    private Double discount;

    public static CostSettingsDto from(CostSettings costSettings) {
        return new CostSettingsDto(costSettings.getAccommodationPrice(), costSettings.getMealPrice(),
                costSettings.getTrainingPrice(), costSettings.getMargin(), costSettings.getDiscount());
    }

    public static CostSettingsDto forClientFrom(CostSettings costSettings) {
        return new CostSettingsDto(costSettings.getAccommodationPrice(), costSettings.getMealPrice(),
                costSettings.getTrainingPrice(), null, null);
    }
}
