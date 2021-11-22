package kg.megacom.referalsystem.controller;

import kg.megacom.referalsystem.model.request.RegisterRequest;
import kg.megacom.referalsystem.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    private final SubscriberService subscriberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        try {
            log.info("Subscriber registration...");
            return ResponseEntity.ok(subscriberService.register(registerRequest));
        } catch (Exception ex) {
            log.error("Registration failed. Check stacktrace below to find cause.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
