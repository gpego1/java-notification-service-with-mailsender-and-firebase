package com.notifications.notification_service.domain.event;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderCreatedEvent {

    private String eventId;
    private Long orderId;
    private Long userId;
    private String userEmail;

    private List<OrderItemData> items;
    private BigDecimal totalAmount;
    private LocalDateTime occurredAt;

    @Getter
    @Setter
    public static class OrderItemData {
        private Long productId;
        private Integer quantity;
        private BigDecimal price;
    }
}
