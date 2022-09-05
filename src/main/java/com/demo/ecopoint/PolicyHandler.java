package com.demo.ecopoint;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.ecopoint.domain.DisposalCompleted;
import com.demo.ecopoint.domain.EcoPointStandard;
import com.demo.ecopoint.domain.PaymentCanceled;
import com.demo.ecopoint.domain.PaymentCompleted;
import com.demo.ecopoint.kafka.KafkaProcessor;
import com.demo.ecopoint.repo.EcoPointRepository;
import com.demo.ecopoint.service.EcoPointService;
import com.demo.ecopoint.service.EcoPointStandardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyHandler {


  private final EcoPointStandardService ecoPointStandardService;
  private final EcoPointService ecoPointService;
  private final EcoPointRepository ecoPointRepository;

  @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){
      System.out.println("KafkaProcessor.INPUT????");
    }

    
  //결제 완료 시, 포인트 차감
  @Transactional
  @StreamListener(KafkaProcessor.INPUT)
  public void wheneverPaymentCompleted(@Payload PaymentCompleted paymentCompleted){
    if(!paymentCompleted.validate())
          return;

          System.out.println("############ wheneverPaymentCompleted ############");
    if(paymentCompleted.getPaymentMethod().equals("EcoPoint")){
      ecoPointService.useEcoPoint(paymentCompleted);
    }
    
  }

  //결제 취소 시, 포인트 환불
  @Transactional
  @StreamListener(KafkaProcessor.INPUT)
  public void wheneverPaymentCanceled(@Payload PaymentCanceled paymentCanceled){
    if(!paymentCanceled.validate())
          return;
    
    System.out.println("############ wheneverPaymentCanceled ############");
    if(paymentCanceled.getPaymentMethod().equals("EcoPoint")){ 
      ecoPointService.refoundPoint(paymentCanceled);
    }
  }

  // @Transactional
  // @StreamListener(KafkaProcessor.INPUT)
  // public void wheneverDisposalCompleted(@Payload DisposalCompleted disposalCompleted){
  //     if(!disposalCompleted.validate())
  //         return;

  //     System.out.println("############ wheneverDisposalCompleted ############");
  //     String productNm = disposalCompleted.getDisposalProduct();
  //     EcoPointStandard ecoPointStandard =  ecoPointStandardService.getEcoPointStandarByClassification(productNm);
  // }
 
}
