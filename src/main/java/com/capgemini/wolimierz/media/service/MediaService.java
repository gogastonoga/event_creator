package com.capgemini.wolimierz.media.service;

import com.capgemini.wolimierz.media.model.Media;
import com.capgemini.wolimierz.media.model.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface MediaService {
    Media findMedia(UUID globalId);

    String updateHomePageSettings(MultipartFile media, MediaType type) throws IOException;

    String updateEventSizeImage(MultipartFile media, UUID parentId, MediaType image) throws IOException;
}
