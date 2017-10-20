package com.capgemini.wolimierz.event.model;

import com.capgemini.wolimierz.event.dto.SeasonDto;
import com.capgemini.wolimierz.utils.LocalDateConverter;
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
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @Column(name = "FROM_DATE", nullable = false)
    private LocalDate fromDate;
    @Column(name = "TO_DATE", nullable = false)
    private LocalDate toDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "GLOBAL_ID", nullable = false, unique = true)
    private UUID globalId;

    public void updateFrom(SeasonDto seasonDto) {
        this.name = seasonDto.getName();
        this.description = seasonDto.getDescription();
        this.fromDate = seasonDto.getFrom();
        this.toDate = seasonDto.getTo();
    }
}
