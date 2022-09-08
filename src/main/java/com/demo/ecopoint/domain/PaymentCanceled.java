package com.demo.ecopoint.domain;


import com.demo.ecopoint.AbstractEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCanceled extends AbstractEvent {
  //결제 취소 시, ecopoint 사용한 경우 환불
  private Long memberId;
  private Long totalPrice;
  private String paymentMethod;
  
}
