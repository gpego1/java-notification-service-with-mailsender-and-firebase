package com.notifications.notification_service.infra.presentation.controller;
import com.notifications.notification_service.application.dto.NotificationRequest;
import com.notifications.notification_service.application.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private FCMService fcmService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        try {
            String messageId = fcmService.senNotification(notificationRequest);
            return new ResponseEntity<>(messageId, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while sending notification: " + e.getMessage());
        }
    }
}
