package com.demo.ecopoint.domain;


import com.demo.ecopoint.AbstractEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCompleted extends AbstractEvent {
  //결제 완료 시, ecopoint 사용한 경우 차감
  private Long memberId;
  private Long price;
  private String paymentMethod;
  

}
