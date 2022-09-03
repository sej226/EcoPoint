package com.demo.ecopoint.domain;


import java.util.List;

import com.demo.ecopoint.AbstractEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisposalCompleted extends AbstractEvent {
    private Long    disposalId;
    private String  disposalProduct;
    private int     quantity;
    private Long    memberId;
    private Long    ecoPoint;
    private String   disposalDate;
    private BranchName    branchName;

    private List<Disposal> disposalItems;
    private Long recycleItemId;
 
    
}
