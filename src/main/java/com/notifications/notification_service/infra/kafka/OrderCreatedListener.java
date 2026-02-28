package com.notifications.notification_service.infra.kafka;
import com.notifications.notification_service.domain.event.OrderCreatedEvent;
import com.notifications.notification_service.infra.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {
    private final EmailService emailService;

    @KafkaListener(
            topics = "order.created",
            groupId = "notification-service"
    )

    public void listen(OrderCreatedEvent event) {
        System.out.println("Received event: " + event.getOrderId());
        System.out.println("Email sent to: " + event.getUserEmail());
        emailService.sendOrderConfirmation(event);
    }
}
