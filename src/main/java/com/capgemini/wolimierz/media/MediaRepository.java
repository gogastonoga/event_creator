package com.capgemini.wolimierz.media;

import com.capgemini.wolimierz.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByGlobalId(UUID globalId);
}
