package com.capgemini.wolimierz.event.repository;

import com.capgemini.wolimierz.event.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findAllByGlobalIdIn(List<UUID> seasonsIds);

    Season findByGlobalId(UUID seasonId);
}
