package com.capgemini.wolimierz.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "FORMS")
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BUDGET_DESCRIPTION", length = 2048)
    private String budgetDescription;
    @Column(name = "ADDITIONAL_DESCRIPTION", length = 2048)
    private String additionalDescription;
    @Column(name = "PARTICIPATNS_DESCRIPTION", length = 2048)
    private String participantsDescription;
    @Column(name = "ACCOMMODATION_DESCRIPTION", length = 2048)
    private String accommodationDescription;
    @Column(name = "DATE_FORM_DESCRIPTION", length = 2048)
    private String dateFormDescription;

    public void uptateFrom(FormDto formDto) {
        this.budgetDescription = formDto.getBudgetDescription();
        this.additionalDescription = formDto.getAdditionalDescription();
        this.participantsDescription = formDto.getParticipantsDescription();
        this.accommodationDescription = formDto.getAccommodationDescription();
        this.dateFormDescription = formDto.getDateFormDescription();
    }
}
