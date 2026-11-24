package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="courses") @Data @NoArgsConstructor public class Course { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true,nullable=false) private String name; private String department; private Integer durationSemesters; }
