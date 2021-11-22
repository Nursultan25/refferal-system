package kg.megacom.referalsystem.service.impl;

import kg.megacom.referalsystem.mappers.InvitationMapper;
import kg.megacom.referalsystem.mappers.SubscriberMapper;
import kg.megacom.referalsystem.model.dto.InvitationDto;
import kg.megacom.referalsystem.model.entity.Invitation;
import kg.megacom.referalsystem.model.entity.Subscriber;
import kg.megacom.referalsystem.model.enums.InvitationStatus;
import kg.megacom.referalsystem.model.request.InvitationSendRequest;
import kg.megacom.referalsystem.model.request.PhoneNumberRequest;
import kg.megacom.referalsystem.model.request.RegisterRequest;
import kg.megacom.referalsystem.repo.InvitationRepo;
import kg.megacom.referalsystem.service.InvitationService;
import kg.megacom.referalsystem.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepo invitationRepo;
    private final SubscriberService subscriberService;

    @Override
    public InvitationDto sendInvite(InvitationSendRequest invitationDto) {
        Subscriber sender = checkOrCreate(invitationDto.getSenderPhoneNumber());
        Subscriber receiver = checkOrCreate(invitationDto.getReceiverPhoneNumber());

        LocalDateTime startDate = LocalDateTime.now();

        invitationRepo.findLast().map(invitation -> {
            invitation.setEndDate(startDate.minusSeconds(1));
            return invitationRepo.save(invitation);
        });

        Invitation invitation = Invitation
                .builder()
                .sender(sender)
                .receiver(receiver)
                .startDate(startDate)
                .invitationStatus(InvitationStatus.NEW)
                .endDate(startDate.plusYears(99))
                .build();


        invitationRepo.save(invitation);

        return InvitationMapper.INSTANCE.toDto(invitation);
    }

    private Subscriber checkOrCreate(String phoneNumber) {
        if (!subscriberService.existsByPhone(phoneNumber)) {
            subscriberService.register(new RegisterRequest(phoneNumber, true));
        }
        return SubscriberMapper.INSTANCE.toEntity(subscriberService.findByPhone(phoneNumber));
    }

    public InvitationDto acceptInvitation(PhoneNumberRequest request) {
        String phone = request.getPhoneNumber();
        if (!subscriberService.isActive(phone)) {
            throw new RuntimeException("Subscriber is not available");
        }
        Invitation invitation = invitationRepo.findLastByReceiverIdAndStatus(phone, InvitationStatus.NEW.toString()).map(inv -> {
            inv.setInvitationStatus(InvitationStatus.ACCEPTED);
            return invitationRepo.save(inv);
        }).orElseThrow(() -> new RuntimeException("No new invitations"));

        invitationRepo.findAllByReceiverAndInvitationStatus(invitation.getReceiver(), InvitationStatus.NEW.toString()).map(invitations -> {
            invitations.forEach(e -> e.setInvitationStatus(InvitationStatus.CANCELLED));
            return invitationRepo.saveAll(invitations);
        });
        return InvitationMapper.INSTANCE.toDto(invitation);
    }

    @Override
    public InvitationDto getLast(String phoneNumber) {
        return null;
    }
}
