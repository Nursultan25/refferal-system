package kg.megacom.referalsystem.controller;

import kg.megacom.referalsystem.model.request.InvitationSendRequest;
import kg.megacom.referalsystem.model.request.PhoneNumberRequest;
import kg.megacom.referalsystem.service.InvitationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invitation")
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping("/send-invitation")
    public ResponseEntity<?> sendInvitation(@RequestBody InvitationSendRequest invitationSendRequest) {
        try {
            return ResponseEntity.ok(invitationService.sendInvite(invitationSendRequest));
        } catch (Exception ex) {
            log.error("Invitation send failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/accept")
    public ResponseEntity<?> acceptInvitation(@RequestBody PhoneNumberRequest request) {
        try {
            return ResponseEntity.ok(invitationService.acceptInvitation(request));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
