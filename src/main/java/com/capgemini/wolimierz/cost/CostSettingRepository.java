package com.capgemini.wolimierz.cost;

import com.capgemini.wolimierz.cost.model.CostSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostSettingRepository extends JpaRepository<CostSetting, Long> {
}
