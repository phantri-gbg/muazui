package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageID")
    private Integer messageId;

    @Column(name = "SenderID", nullable = false)
    private Integer senderId;

    @Column(name = "ReceiverID", nullable = false)
    private Integer receiverId;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "SentAt")
    private LocalDateTime sentAt;
}