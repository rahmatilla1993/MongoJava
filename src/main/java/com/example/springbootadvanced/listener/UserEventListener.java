package com.example.springbootadvanced.listener;

import com.example.springbootadvanced.events.OtpGenerateEvent;
import com.example.springbootadvanced.events.SendMailEvent;
import com.example.springbootadvanced.user.User;
import com.example.springbootadvanced.user.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final OtpService otpService;
    @TransactionalEventListener(
            classes = {OtpGenerateEvent.class},
            phase = TransactionPhase.AFTER_COMMIT,
            condition = "#event.user.email ne null"
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public CompletableFuture<SendMailEvent> generateOtpEventListener(OtpGenerateEvent event) throws InterruptedException {
        User user = event.getUser();
        TimeUnit.SECONDS.sleep(3);
        otpService.generateOtp(user);
        log.info("User : {}", user);
        return CompletableFuture.completedFuture(new SendMailEvent(user.getOtp(), user.getEmail()));
    }

    @EventListener(classes = {SendMailEvent.class})
    public void verifyMailSenderListener(SendMailEvent event) {
        log.info("Send mail : {}", event);
    }
}
