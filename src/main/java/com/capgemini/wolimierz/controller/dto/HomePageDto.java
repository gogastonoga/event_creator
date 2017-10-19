package com.capgemini.wolimierz.controller.dto;

import com.capgemini.wolimierz.form.HomePageSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomePageDto extends DescriptionDto {

    private String backgroundVideoUrl;
    private String backgroundImageUrl;

    public HomePageDto(String description) {
        super(description);
    }

    public HomePageDto(String description, String backgroundVideoUrl, String backgroundImageUrl) {
        super(description);
        this.backgroundVideoUrl = backgroundVideoUrl;
        this.backgroundImageUrl = backgroundImageUrl;

    }

    public static HomePageDto from(HomePageSettings homePageSettings) {
        return new HomePageDto(homePageSettings.getDescription());
    }

}
