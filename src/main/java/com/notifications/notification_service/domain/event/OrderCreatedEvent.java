package com.notifications.notification_service.domain.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreatedEvent {

    private String eventId;
    private Long orderId;
    private Long userId;
    private String userEmail;

    private List<OrderItemData> items;
    private BigDecimal totalAmount;
    private LocalDateTime occurredAt;

    public OrderCreatedEvent() {}

    @Data
    @AllArgsConstructor
    public static class OrderItemData {
        private Long productId;
        private Integer quantity;
        private BigDecimal price;

        public OrderItemData() {}
    }
}
