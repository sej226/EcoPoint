package com.demo.ecopoint.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter @Setter
public class Disposal {
    @Id
    @GeneratedValue
    private Long     disposalId;
    private String   disposalProduct;   //배출 제품
    private int      quantity;          //배출 수량
    private Long     ecoPoint;          
    private String   disposalDate;  
    private String    branchName;     //배출 지점
    private Long userId;

    //
    // @PrePersist : Persist(insert)메서드가 호출되기 전에 실행되는 메서드
    // @PreUpdate : merge메서드가 호출되기 전에 실행되는 메서드
    // @PreRemove : Delete메서드가 호출되기 전에 실행되는 메서드
    // @PostPersist : Persist(insert)메서드가 호출된 이후에 실행되는 메서드
    // @PostUpdate : merge메서드가 호출된 후에 실행되는 메서드
    // @PostRemove : Delete메서드가 호출된 후에 실행되는 메서드
    // @PostLoad : Select조회가 일어난 직후에 실행되는 메서드

    @PostPersist // Persist(insert)메서드가 호출된 이후에 실행되는 메서드
    public void onPostPersist( ){
        System.out.println("########################PostPersist ###########################");
        // DisposalCompleted  disposalCompleted  = new DisposalCompleted();
        // disposalCompleted.setDisposalProduct("disposalProduct");  
        
        // disposalCompleted.publishAfterCommit();
    }
}
