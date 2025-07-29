package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminSubscriptionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    private String instituteEmail;
    private String status = "Active";
    private String transactionId;
    private Float amount;
    private String paymentMode;
    private Integer gstPercent;
    private Float gstAmount;
    private Float totalAmount;
    private String invoiceNo;

    private String planName;
    private String sectionName;
    private String studentRange;
    private String employeeRange;
    private Integer validity;
    private Float discountper;
    private boolean isCreatedByAdmin;
    private int branchLimit;
    private LocalDate subscriptstartDate;
    private LocalDate subscriptendDate;


}
