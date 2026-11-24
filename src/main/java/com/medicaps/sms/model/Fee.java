package com.medicaps.sms.model;
import jakarta.persistence.*; import lombok.*; import java.time.*; import java.math.*;
@Entity @Table(name="fees") @Data @NoArgsConstructor public class Fee { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true,nullable=false) private String studentId; private BigDecimal totalFees=BigDecimal.ZERO; private BigDecimal paidFees=BigDecimal.ZERO; private BigDecimal pendingFees=BigDecimal.ZERO; private LocalDate updatedAt=LocalDate.now(); }
