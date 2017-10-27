package com.capgemini.wolimierz.event.predefined;

import lombok.Getter;

@Getter
public enum PredefinedSize {
    /**
     * Event for 1-20 people
     */
    SMALL(1, 20, "1", 0),
    /**
     * Event for 21-45 people
     */
    MEDIUM(21, 45,  "2", -10),

    /**
     * Event for 45-100 people
     */
    LARGE(45, 100, "3", -20);

    private final String description;
    private final int lowerBound;
    private final int higherBound;
    private final double price;

    PredefinedSize(int lowerBound, int higherBound, String description, double price) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.description = description;
        this.price = price;
    }
}
