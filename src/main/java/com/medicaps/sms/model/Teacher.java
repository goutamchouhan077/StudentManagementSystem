package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="teachers") @Data @NoArgsConstructor public class Teacher { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true) private String employeeId; private String fullName; private String email; private String phone; private String department; }
