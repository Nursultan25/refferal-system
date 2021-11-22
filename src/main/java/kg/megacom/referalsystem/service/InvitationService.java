package kg.megacom.referalsystem.service;

import kg.megacom.referalsystem.model.dto.InvitationDto;
import kg.megacom.referalsystem.model.request.InvitationSendRequest;
import kg.megacom.referalsystem.model.request.PhoneNumberRequest;
import org.springframework.stereotype.Service;

@Service
public interface InvitationService {
    InvitationDto sendInvite(InvitationSendRequest invitationDto);
    InvitationDto getLast(String phoneNumber);
    InvitationDto acceptInvitation(PhoneNumberRequest request);
}
