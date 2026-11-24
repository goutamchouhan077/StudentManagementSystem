package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="students") @Data @NoArgsConstructor public class Student { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true,nullable=false) private String studentId; @Column(nullable=false) private String fullName; private String email; private String phone; private String department; private String course; private Integer semester; private LocalDate admissionDate; private String gender; private String address; }
