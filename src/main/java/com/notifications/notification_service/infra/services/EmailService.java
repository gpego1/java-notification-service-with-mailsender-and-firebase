package com.notifications.notification_service.infra.services;
import com.notifications.notification_service.domain.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOrderConfirmation(OrderCreatedEvent event) {
    SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getUserEmail());
        message.setSubject("Order confirmation: #" + event.getOrderId());

        message.setText(
                "Hello! \n\n" +
                "Your order was successfully registered. \n\n" +
                "Order: " + event.getOrderId() + "\n" +
                "Total: $ " + event.getTotalAmount() + "\n\n" +
                "Thank you for trusting our system!"
                );
        mailSender.send(message);
    }
}
