package com.capgemini.wolimierz.media.service;

import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.event.repository.EventSizeRepository;
import com.capgemini.wolimierz.form.HomePageSettings;
import com.capgemini.wolimierz.form.repository.HomePageSettingsRepository;
import com.capgemini.wolimierz.media.MediaRepository;
import com.capgemini.wolimierz.media.model.Media;
import com.capgemini.wolimierz.media.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final HomePageSettingsRepository homePageSettingsRepository;
    private final EventSizeRepository eventSizeRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository, HomePageSettingsRepository homePageSettingsRepository,
                            EventSizeRepository eventSizeRepository) {
        this.mediaRepository = mediaRepository;
        this.homePageSettingsRepository = homePageSettingsRepository;
        this.eventSizeRepository = eventSizeRepository;
    }

    @Override
    public Media findMedia(UUID globalId) {
        return mediaRepository.findByGlobalId(globalId);
    }

    @Override
    public String updateHomePageSettings(MultipartFile image, MediaType type) throws IOException {
        HomePageSettings homePageSettings = homePageSettingsRepository.findAll().get(0);
        Media media = createMedia(image, type);
        if (MediaType.IMAGE.equals(type)) {
            media.setHomePageSettingsImage(homePageSettings);
            homePageSettings.setImage(media);
        } else {
            media.setHomePageSettingsVideo(homePageSettings);
            homePageSettings.setVideo(media);
        }
        return save(media);
    }

    @Override
    public String updateEventSizeImage(MultipartFile image, UUID parentId, MediaType type) throws IOException {
        EventSize eventSize = Optional.ofNullable(eventSizeRepository.findByGlobalId(parentId))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Event size for id: %s not found", parentId)));
        Media media = createMedia(image, type);
        media.setEventSizeImage(eventSize);
        eventSize.setImage(media);
        return save(media);
    }

    private String save(Media media) {
        return mediaRepository.save(media).getFileName();
    }

    private Media createMedia(MultipartFile image, MediaType type) throws IOException {
        return new Media(image.getOriginalFilename(), type, image.getBytes());
    }
}
