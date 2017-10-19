package com.capgemini.wolimierz.media.model;

import com.capgemini.wolimierz.event.model.EventSize;
import com.capgemini.wolimierz.form.HomePageSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEDIA")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEDIA_TYPE")
    private MediaType type;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    @Setter
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "HOME_PAGE_SETTINGS_IMAGE")
    private HomePageSettings homePageSettingsImage;

    @Setter
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "HOME_PAGE_SETTINGS_VIDEO")
    private HomePageSettings homePageSettingsVideo;

    @Setter
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "EVENT_SIZE_IMAGE")
    private EventSize eventSizeImage;

    public Media(String fileName, MediaType type, byte[] data) {
        this.fileName = fileName;
        this.type = type;
        this.data = data;
        globalId = UUID.randomUUID();
    }
}
