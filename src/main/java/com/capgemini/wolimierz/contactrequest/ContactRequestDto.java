package com.capgemini.wolimierz.contactrequest;

import com.capgemini.wolimierz.contactrequest.model.ContactRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {
    @NotNull
    private String message;
    @NotNull
    private String topic;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    @NotNull
    private String creatorMail;
    private UUID globalId;
    private LocalDateTime creationTime;

    public static ContactRequestDto from(ContactRequest contactRequest) {
        return new ContactRequestDto(contactRequest.getMessage(), contactRequest.getTopic(), contactRequest.getName(),
                contactRequest.getSurname(), contactRequest.getCreatorEmail(), contactRequest.getGlobalId(),
                contactRequest.getCreationTime());
    }
}
