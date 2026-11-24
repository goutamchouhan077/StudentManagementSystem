package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="attendance", uniqueConstraints=@UniqueConstraint(columnNames={"student_id","attendance_date","course"})) @Data @NoArgsConstructor public class Attendance { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String studentId; @Column(nullable=false) private LocalDate attendanceDate; private String course; @Column(nullable=false) private String status; private String markedBy; }
