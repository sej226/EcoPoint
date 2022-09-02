package com.demo.ecopoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecopoint.domain.Disposal;
import com.demo.ecopoint.domain.DisposalCompleted;
import com.demo.ecopoint.domain.EcoPointStandard;
import com.demo.ecopoint.service.DisposalService;
import com.demo.ecopoint.service.EcoPointService;
import com.demo.ecopoint.service.EcoPointStandardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("disposal")
@RequiredArgsConstructor
public class DisposalController {

  private final DisposalService disposalService;
  private final EcoPointService ecoPointService;
  private final EcoPointStandardService ecoPointStandardService;

    @PostMapping("/disposalItem")
    public ResponseEntity disposalItem(@RequestBody Disposal request) {
      
        log.info("DisposalId = {}, DisposaProduct = {}, DisposalPlace = {}, ecoPoint = {}"
        , request.getDisposalId(), request.getDisposalProduct(), request.getBranchName(), request.getEcoPoint());

        Disposal disposal = disposalService.disposalItem(request);
        if(disposal != null) {
            DisposalCompleted  disposalCompleted  = new DisposalCompleted();
            disposalCompleted.setDisposalId(disposal.getDisposalId());
            disposalCompleted.setDisposalProduct(request.getDisposalProduct());
            disposalCompleted.setQuantity(request.getQuantity());
            disposalCompleted.setMemberId(request.getUserId());
            disposalCompleted.setDisposalDate(request.getDisposalDate());
            disposalCompleted.setBranchName(request.getBranchName());

            EcoPointStandard ecoPointStandard = ecoPointStandardService.getEcoPointStandarByClassification(disposalCompleted.getDisposalProduct());
            System.out.println("getEcoPointStandarByClassification  " + ecoPointStandard.getClassification());
            long point = ecoPointStandard.getEcoPoint() * disposalCompleted.getQuantity();
            System.out.println(point + " ###################################################");
            disposalCompleted.setEcoPoint(point);
            disposalCompleted.publishAfterCommit();
            
            disposalService.editDisposal(disposalCompleted.getDisposalId(), point);
           
            if(ecoPointService.addEcoPoint(disposalCompleted).equals("Success")) {
                
                System.out.println("addEcoPoint Success!");
                return new ResponseEntity(HttpStatus.CREATED);
            }

            return new ResponseEntity(HttpStatus.CREATED);
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping
    public ResponseEntity getDisposalList() {
        return ResponseEntity.ok().body(disposalService.getDisposalList());
    }


}
