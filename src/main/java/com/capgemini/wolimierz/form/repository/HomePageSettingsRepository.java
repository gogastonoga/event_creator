package com.capgemini.wolimierz.form.repository;

import com.capgemini.wolimierz.form.HomePageSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface HomePageSettingsRepository extends JpaRepository<HomePageSettings, Long> {
}
