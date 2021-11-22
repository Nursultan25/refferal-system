package kg.megacom.referalsystem.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberDto {
    Long id;
    String phone;
    Boolean isActive;
    LocalDateTime editDate;
}
