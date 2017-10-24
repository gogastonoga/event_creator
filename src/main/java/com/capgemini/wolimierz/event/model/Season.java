package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.event.dto.SeasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "SEASONS")
@NoArgsConstructor
@AllArgsConstructor
public class Season implements Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @Column(name = "FROM_DATE", nullable = false)
    private LocalDate fromDate;
    @Column(name = "TO_DATE", nullable = false)
    private LocalDate toDate;
    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;
    @Column(name = "PRICE")
    private double price;

    public void updateFrom(SeasonDto seasonDto) {
        this.name = seasonDto.getName();
        if (seasonDto.getFrom() != null) {
            this.fromDate = seasonDto.getFrom();
        }
        if (seasonDto.getTo() != null) {
            this.toDate = seasonDto.getTo();
        }
    }

    @Override
    public double getCostFactor() {
        return price;
    }

    @Override
    public void setCostFactor(double costFactor) {
        this.price = costFactor;
    }
}