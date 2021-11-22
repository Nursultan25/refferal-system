package kg.megacom.referalsystem.model.dto;


import kg.megacom.referalsystem.model.enums.InvitationStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvitationDto {
    Long id;
    SubscriberDto sender;
    SubscriberDto receiver;
    LocalDateTime startDate;
    LocalDateTime endDate;
    InvitationStatus status;
}
