package com.notifications.notification_service.infra.kafka;

import com.notifications.notification_service.domain.event.OrderCreatedEvent;
import com.notifications.notification_service.infra.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedListener {

    private final EmailService emailService;

    @KafkaListener(topics = "order.created",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(OrderCreatedEvent event) {
        log.info("========== MENSAGEM RECEBIDA DO KAFKA ==========");
        log.info("Event ID: {}", event.getEventId());
        log.info("Order ID: {}", event.getOrderId());
        log.info("User ID: {}", event.getUserId());
        log.info("User Email: {}", event.getUserEmail());
        log.info("Total Amount: {}", event.getTotalAmount());
        log.info("Occurred At: {}", event.getOccurredAt());

        if (event.getItems() != null) {
            log.info("Items count: {}", event.getItems().size());
            event.getItems().forEach(item -> {
                log.info("  - Product ID: {}, Quantity: {}, Price: {}",
                        item.getProductId(), item.getQuantity(), item.getPrice());
            });
        }

        try {
            emailService.sendOrderConfirmation(event);
            log.info("Email enviado com sucesso para: {}", event.getUserEmail());
        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage(), e);
        }
    }
}