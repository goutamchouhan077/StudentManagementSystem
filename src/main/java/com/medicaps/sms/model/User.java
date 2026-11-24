package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="users") @Data @NoArgsConstructor public class User { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true,nullable=false) private String username; private String name; @Column(unique=true) private String email; @Column(nullable=false) private String password; @Column(nullable=false) private String role; private boolean active=true; }
