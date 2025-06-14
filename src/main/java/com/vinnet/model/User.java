package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID") // Loại bỏ nullable = true
    private Integer userId;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role")
    private String role;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "Status", columnDefinition = "BIT DEFAULT 1")
    private Boolean status;

    @Column(name = "IsSeller", columnDefinition = "BIT DEFAULT 0")
    private Boolean isSeller;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "ProfilePicture")
    private String profilePicture;
}
