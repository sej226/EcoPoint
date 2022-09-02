package com.demo.ecopoint.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

import com.demo.ecopoint.EcoPointApplication;
import com.demo.ecopoint.kafka.KafkaProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table
public class EcoPointStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long standardId;

    @Column(nullable = false)
    private String classification;  //재활용품 분류

    @Column(nullable = false)
    private long countweightstandard;
    
    @Column(nullable = false)
    private String standardDesc;  

    @Column(nullable = false)
    private long ecoPoint;

    // @PostPersist
    // public void eventPublish(){
    //     ProductChanged productChanged = new ProductChanged();
    //     productChanged.setStandardId(this.getStandardId());
    //     productChanged.setClassification(this.getClassification());
    //     productChanged.setCountweightstandard(this.getCountweightstandard());
    //     productChanged.setStandardDesc(this.getStandardDesc());
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String json = null;

    //     try {
    //         json = objectMapper.writeValueAsString(productChanged);
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("JSON format exception", e);
    //     }
    // System.out.println(json);
        
    //     KafkaProcessor processor = DemoApplication.getApplicationContext().getBean(KafkaProcessor.class);
    //     MessageChannel outputChannel = processor.outboundTopic();

    //     outputChannel.send(MessageBuilder
    //         .withPayload("json")
    //         .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
    //         .build());
    // }

    
  
}


