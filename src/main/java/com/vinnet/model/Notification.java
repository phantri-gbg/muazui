package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Integer notificationId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "Message", nullable = false)
    private String message;

    @Column(name = "IsRead")
    private Boolean isRead;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
