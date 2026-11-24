package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="marks") @Data @NoArgsConstructor public class Mark { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String studentId; private String subject; private String course; private Integer semester; private Double obtainedMarks; private Double maximumMarks; private String grade; }
