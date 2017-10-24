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
    @Column(name = "SUMMARY_DESCRIPTION", length = 2048)
    private String summaryDescription;

    public void uptateFrom(FormDto formDto) {
        if (!formDto.getBudgetDescription().isEmpty()) {
            this.budgetDescription = formDto.getBudgetDescription();
        }
        if (!formDto.getAdditionalDescription().isEmpty()) {
            this.additionalDescription = formDto.getAdditionalDescription();
        }
        if (!formDto.getParticipantsDescription().isEmpty()) {
            this.participantsDescription = formDto.getParticipantsDescription();
        }
        if (!formDto.getAccommodationDescription().isEmpty()) {
            this.accommodationDescription = formDto.getAccommodationDescription();
        }
        if (!formDto.getDateFormDescription().isEmpty()) {
            this.dateFormDescription = formDto.getDateFormDescription();
        }
        if (!formDto.getSummaryDescription().isEmpty()) {
            this.summaryDescription = formDto.getSummaryDescription();
        }
    }

}
