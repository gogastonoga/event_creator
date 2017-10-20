package com.capgemini.wolimierz.event.model;

import java.util.UUID;

public interface Cost {
    double getCostFactor();

    String getName();

    UUID getGlobalId();

    void setCostFactor(double costFactor);
}
