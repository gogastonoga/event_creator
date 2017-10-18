package com.capgemini.wolimierz.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "HOME_PAGE_SETTINGS")
@AllArgsConstructor
@NoArgsConstructor
public class HomePageSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "DESCRIPTION", nullable = false, length = 2048)
    private String description;

    @Setter
    @Column(name = "BACKOGRUND_IMAGE_URL")
    private String backgroundImageUrl;

    @Setter
    @Column(name = "BACKGROUND_VIDEO_URL")
    private String backgroundVideoUrl;
}
