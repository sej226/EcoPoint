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


@NoArgsConstructor
@AllArgsConstructor
// @Table
@Builder
@Entity
@Getter @Setter
public class EcoPoint {
    
    @Id   
    private Long memberId;
    private Long ecoPoint;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    // @PrePersist : Persist(insert)메서드가 호출되기 전에 실행되는 메서드
    // @PreUpdate : merge메서드가 호출되기 전에 실행되는 메서드
    // @PreRemove : Delete메서드가 호출되기 전에 실행되는 메서드
    // @PostPersist : Persist(insert)메서드가 호출된 이후에 실행되는 메서드
    // @PostUpdate : merge메서드가 호출된 후에 실행되는 메서드
    // @PostRemove : Delete메서드가 호출된 후에 실행되는 메서드
    // @PostLoad : Select조회가 일어난 직후에 실행되는 메서드

    @PostPersist
    public void onPostPersist(){
        
    }
  
}


