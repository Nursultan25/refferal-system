package kg.megacom.referalsystem.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvitationSendRequest {
    String senderPhoneNumber;
    String receiverPhoneNumber;
}
