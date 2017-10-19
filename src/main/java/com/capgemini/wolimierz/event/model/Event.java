package com.capgemini.wolimierz.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EVENTS")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Organizer organizer;

    //TODO one and only one of the following must not be null: time / seasons/ weekend / days
    @Column(name = "EVENT_TIME")
    private LocalDateTime eventTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EVENT_X_EVENT_TYPE", joinColumns =
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "EVENT_TYPE_ID", referencedColumnName = "ID")
    )
    private List<EventType> types = new ArrayList<>();

    @Column(name = "ROOMS")
    private Integer rooms;

    @Column(name = "GUESTS_NUMBER")
    private Integer guestsNumber;

    @ManyToOne
    @JoinColumn(name = "EVENT_SIZE_ID", referencedColumnName = "id")
    private EventSize size;

    @Column(name = "NIGHTS")
    private Integer nights;

    @Column(name = "ADDITIONAL_REQUIREMENTS", length = 2048)
    private String additionalRequirements;

    @Column(name = "BUDGET")
    private Double budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "KIND_OF_DAYS")
    private KindOfDays kindOfDays;

    @ManyToOne
    @JoinColumn(name = "SEASON_ID", referencedColumnName = "id")
    private Season season;

    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    @Column(name = "ESTIMATED_COST")
    private Double estimatedCost;
}
