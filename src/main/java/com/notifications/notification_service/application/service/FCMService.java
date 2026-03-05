package com.notifications.notification_service.application.service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.notifications.notification_service.application.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public String senNotification(NotificationRequest request) throws FirebaseMessagingException {
        Notification noti = Notification.builder()
                .setTitle(request.title())
                .setBody(request.body())
                .build();

        Message message = Message.builder()
                .setToken(request.token())
                .setNotification(noti)
                .build();

        return firebaseMessaging.send(message);
    }
}
