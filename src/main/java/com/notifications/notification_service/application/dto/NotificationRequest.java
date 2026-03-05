package com.notifications.notification_service.application.dto;

public record NotificationRequest(
        String title,
        String body,
        String token
) {
}
