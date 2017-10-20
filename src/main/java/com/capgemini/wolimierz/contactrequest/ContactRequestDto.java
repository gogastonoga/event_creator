package com.capgemini.wolimierz.contactrequest;

import com.capgemini.wolimierz.contactrequest.model.ContactRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {
    @NotNull
    private String message;
    @NotNull
    private String creatorMail;
    private UUID globalId;

    public static ContactRequestDto from(ContactRequest contactRequest) {
        return new ContactRequestDto(contactRequest.getMessage(), contactRequest.getCreatorEmail(),
                contactRequest.getGlobalId());
    }
}
