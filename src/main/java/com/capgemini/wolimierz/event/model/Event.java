package com.capgemini.wolimierz.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_TYPE_ID")
    private List<EventType> types;

    @Column(name = "ROOMS")
    private int rooms;

    @Column(name = "USERS_NUMBER")
    private int usersNumber;

    @OneToOne
    private EventSize size;

    @Column(name = "NIGHTS")
    private int nights;

    @Column(name = "ADDITIONAL_REQUIREMENTS", length = 2048)
    private String additionalRequirements;

    @Column(name = "MAX_COST")
    private double maxCost;//TODO better name

    @Enumerated(EnumType.STRING)
    @Column(name = "KIND_OF_DAYS")
    private KindOfDays kindOfDays;

    @Column(name = "SEASON_ID")
    private UUID seasonId;

    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;
}
