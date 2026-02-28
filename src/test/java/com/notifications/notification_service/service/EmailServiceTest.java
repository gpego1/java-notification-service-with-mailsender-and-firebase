package com.notifications.notification_service.service;

import com.notifications.notification_service.infra.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.notifications.notification_service.domain.event.OrderCreatedEvent;

import java.math.BigDecimal;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(1L);
        event.setUserEmail("seu-email-teste@gmail.com");
        event.setTotalAmount(BigDecimal.valueOf(99.99));

        emailService.sendOrderConfirmation(event);
    }
}