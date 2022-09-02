package com.demo.ecopoint.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class ProductChanged {

  private long standardId;

  private String classification;  //재활용품 분류

  private long countweightstandard;
    
    private String standardDesc;  

    private long ecoPoint;

  // public ProductChanged(){
  //         this.classification = this.getClass().getSimpleName();
  // }
}
