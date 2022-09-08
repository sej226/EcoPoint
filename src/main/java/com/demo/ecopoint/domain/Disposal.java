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

}
