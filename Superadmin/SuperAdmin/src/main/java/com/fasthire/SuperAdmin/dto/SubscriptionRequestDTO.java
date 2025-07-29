package com.fasthire.SuperAdmin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDTO {
    private String instituteEmail;
    private Long planId;
    private String transactionId;
    private Float amount;
    private String paymentMode;
    private Integer validity;
    private Integer gstPercent;
    private Float discountPer;
    private boolean isCreatedByAdmin;
    private Float gstAmount;

}
