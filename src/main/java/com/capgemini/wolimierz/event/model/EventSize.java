package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.controller.dto.EventSizeDto;
import com.capgemini.wolimierz.event.predefined.PredefinedSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "EVENT_SIZES")
@NoArgsConstructor
public class EventSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "LOWER_BOUND")
    private int lowerBound;
    @Column(name = "HIGHER_BOUND")
    private int higherBound;
    @Column(name = "PHOTO_URL")
    private String imageUrl;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    public EventSize(int lowerBound, int higherBound, String imageUrl, String description) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.imageUrl = imageUrl;
        this.description = description;
        this.globalId = UUID.randomUUID();
    }

    public EventSize(PredefinedSize size) {
        this(size.getLowerBound(), size.getHigherBound(), size.getImageUrl(), size.getDescription());
    }

    public void updateFrom(EventSizeDto eventSizeDto) {
        this.description = eventSizeDto.getDescription();
        this.imageUrl = eventSizeDto.getImageUrl();
    }
}
