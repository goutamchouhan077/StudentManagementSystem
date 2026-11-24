package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="subjects") @Data @NoArgsConstructor public class Subject { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String code; @Column(nullable=false) private String name; private String course; private Integer semester; private Double maximumMarks=100.0; }
