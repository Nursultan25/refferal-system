package kg.megacom.referalsystem.service.impl;

import kg.megacom.referalsystem.mappers.SubscriberMapper;
import kg.megacom.referalsystem.model.dto.SubscriberDto;
import kg.megacom.referalsystem.model.entity.Subscriber;
import kg.megacom.referalsystem.model.request.RegisterRequest;
import kg.megacom.referalsystem.repo.SubscriberRepo;
import kg.megacom.referalsystem.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepo subscriberRepo;

    @Override
    public SubscriberDto register(RegisterRequest registerRequest) {
        if (subscriberRepo.existsByPhone(registerRequest.getPhone())) {
            throw new RuntimeException("Subscriber with phone: " + registerRequest.getPhone() + " is already registered.");
        }

        Subscriber subscriber = Subscriber
                .builder()
                .phone(registerRequest.getPhone())
                .isActive(registerRequest.getIsActive())
                .build();

        subscriberRepo.save(subscriber);

        return SubscriberMapper.INSTANCE.toDto(subscriber);
    }

    @Override
    public SubscriberDto findByPhone(String phone) {
        Subscriber subscriber = subscriberRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Subscriber not found by phone: " + phone));

        return SubscriberMapper.INSTANCE.toDto(subscriber);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return subscriberRepo.existsByPhone(phone);
    }

    @Override
    public boolean isActive(String phone) {
        return subscriberRepo.isActive(phone);
    }
}
