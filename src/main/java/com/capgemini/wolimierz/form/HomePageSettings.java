package com.capgemini.wolimierz.form;

import com.capgemini.wolimierz.media.model.Media;
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
    @OneToOne(mappedBy = "homePageSettingsImage")
    private Media image;

    @Setter
    @OneToOne(mappedBy = "homePageSettingsVideo")
    private Media video;
}
