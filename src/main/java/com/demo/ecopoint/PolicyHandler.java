package com.demo.ecopoint;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.ecopoint.domain.DisposalCompleted;
import com.demo.ecopoint.domain.EcoPointStandard;
import com.demo.ecopoint.kafka.KafkaProcessor;
import com.demo.ecopoint.service.EcoPointStandardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyHandler {


  private final EcoPointStandardService ecoPointStandardService;

  @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){
      System.out.println("KafkaProcessor.INPUT????");
    }

    
  //배출할 때, 카프카 이벤트 받아서.. 해당 포인트 기준 전달해줘야함 

  @Transactional
  @StreamListener(KafkaProcessor.INPUT)
  public void wheneverDisposalCompleted(@Payload DisposalCompleted disposalCompleted){
      if(!disposalCompleted.validate())
          return;

      System.out.println("############ wheneverDisposalCompleted ############");
      String productNm = disposalCompleted.getDisposalProduct();
      EcoPointStandard ecoPointStandard =  ecoPointStandardService.getEcoPointStandarByClassification(productNm);

      

      // 포인트 측정

      // // 배송 생성
      // Delivery delivery = new Delivery();
      // delivery.setDeliveryAddress(deliveryAddress);
      // delivery.setTrackingNumber(generateTrackingnumber());
      // delivery.setCourier(courier);
      // delivery.setMemberId(paymentCompleted.getMemberId());
      // delivery.setDeliveryStatus(DeliveryStatus.READY);
      // deliveryRepository.save(delivery);
  }
 
}
