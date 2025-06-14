package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserBehavior")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBehavior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BehaviorID")
    private Integer behaviorId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "BehaviorType", nullable = false)
    private String behaviorType;

    @Column(name = "BehaviorData")
    private String behaviorData;

    @Column(name = "BehaviorDate")
    private LocalDateTime behaviorDate;
}
