package kg.megacom.referalsystem.service;

import kg.megacom.referalsystem.model.dto.SubscriberDto;
import kg.megacom.referalsystem.model.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface SubscriberService {
    SubscriberDto register(RegisterRequest registerRequest);
    SubscriberDto findByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean isActive(String phone);

}
