package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.media.model.Media;
import com.capgemini.wolimierz.media.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RequestMapping(path = "/wolimierz/media")
@CrossOrigin(origins = {"http://localhost:4200", "10.42.96.238:4200"})
@RestController
public class MediaController extends BaseController{

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @RequestParam("id") UUID globalId) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        Media media = mediaService.findMedia(globalId);
        if (media == null) {
            throw new IllegalArgumentException(String.format("Media with id: %s not found", globalId));
        }
        StreamUtils.copy(media.getData(), response.getOutputStream());
    }

    @PreAuthorize(ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/image", method = RequestMethod.POST, params = "parent=home_page")
    public String uploadHomePageImage(@RequestParam(name = "image") MultipartFile media) throws IOException {
        return mediaService.updateHomePageSettings(media, com.capgemini.wolimierz.media.model.MediaType.IMAGE);
    }

    @PreAuthorize(ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/image", method = RequestMethod.POST, params = "parent=event_size")
    public String uploadEventSizeImage(@RequestParam(name = "image") MultipartFile media,
                                       @RequestParam("parentId") UUID parentId) throws IOException {
        return mediaService.updateEventSizeImage(media, parentId, com.capgemini.wolimierz.media.model.MediaType.IMAGE);
    }

    @PreAuthorize(ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/video", method = RequestMethod.POST)
    public String uploadHomePageVideo(@RequestParam(name = "video") MultipartFile media) throws IOException {
        return mediaService.updateHomePageSettings(media, com.capgemini.wolimierz.media.model.MediaType.VIDEO);
    }
}
