package com.capgemini.wolimierz.event.predefined;

import lombok.Getter;

@Getter
public enum PredefinedEventType {
    VOLUNTEERING("Wolontariat pracowniczy", "Description1"),
    INTEGRATION("Integracja", "Description2"),
    ENTERTAINMENT("Rozrywka", "Description3"),
    WORKSHOPS("Warsztaty / zajęcia kreatywne", "Description4"),
    TRAINING("Szkolenia z własnymi trenerami", "Description5");

    private final String translation;
    private final String description;

    PredefinedEventType(String translation, String description) {
        this.translation = translation;
        this.description = description;
    }
}
