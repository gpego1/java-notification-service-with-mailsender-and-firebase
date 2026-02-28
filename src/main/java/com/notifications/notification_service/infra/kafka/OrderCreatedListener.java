package com.notifications.notification_service.infra.kafka;
import com.notifications.notification_service.domain.event.OrderCreatedEvent;
import com.notifications.notification_service.infra.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCreatedListener {
    private final EmailService emailService;

    public OrderCreatedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "order.created",
            groupId = "notification-service-test",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(OrderCreatedEvent event) {
        log.info("========== MENSAGEM RECEBIDA DO KAFKA ==========");
        log.info("Event ID: {}", event.getEventId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("User Email: {}", event.getUserEmail());
        log.info("Total Amount: {}", event.getTotalAmount());
        log.info("Occurred At: {}", event.getOccurredAt());

        if (event.getItems() != null) {
            log.info("Items count: {}", event.getItems().size());
        }
        try {
            emailService.sendOrderConfirmation(event);
            log.info("Email enviado com sucesso para: {}", event.getUserEmail());
        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage(), e);
        }
    }


}
