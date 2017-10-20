package com.capgemini.wolimierz.event.predefined;

import lombok.Getter;

@Getter
public enum PredefinedSize {
    /**
     * Event for 1-20 people
     */
    SMALL(1, 20, "https://i.ytimg.com/vi/pVrDRLOeMKY/hqdefault.jpg", "1", 50.0),
    /**
     * Event for 21-45 people
     */
    MEDIUM(21, 45, "https://i.ytimg.com/vi/pVrDRLOeMKY/hqdefault.jpg", "2", 100.0),

    /**
     * Event for 45-100 people
     */
    LARGE(45, 100, "https://i.ytimg.com/vi/pVrDRLOeMKY/hqdefault.jpg", "3", 150.0);

    private final String description;
    private final int lowerBound;
    private final int higherBound;
    private final String imageUrl;
    private final double price;

    PredefinedSize(int lowerBound, int higherBound, String imageUrl, String description, double price) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }
}
