package com.demo.ecopoint.controller;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecopoint.domain.BranchName;
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
        
        //지점명 랜덤으로 지정
        request.setBranchName(BranchName.values()[new Random().nextInt(BranchName.values().length)].toString());
        
        //배출 내역 저장
        Disposal disposal = disposalService.disposalItem(request);
        if(disposal != null) {
            //배출 완료
            DisposalCompleted  disposalCompleted  = new DisposalCompleted();
            disposalCompleted.setDisposalId(disposal.getDisposalId());          //배출 ID
            disposalCompleted.setDisposalProduct(request.getDisposalProduct()); //배출 제품
            disposalCompleted.setQuantity(request.getQuantity());           //배출 수량
            disposalCompleted.setMemberId(request.getUserId());             //배출자 ID
            disposalCompleted.setDisposalDate(request.getDisposalDate());   //배출 일자
            disposalCompleted.setBranchName(request.getBranchName());       //배출 지점

            //배출 제품의 포인트 기준 정보 조회
            EcoPointStandard ecoPointStandard = ecoPointStandardService.getEcoPointStandarByClassification(request.getDisposalProduct());
            // System.out.println("getEcoPointStandarByClassification  " + ecoPointStandard.getClassification());
            long point = ecoPointStandard.getEcoPoint() * disposalCompleted.getQuantity(); //수량만큼 포인트 계산
            System.out.println("############## Point : " + point + " #####################################");
            disposalCompleted.setEcoPoint(point);   //배출 포인트

            disposalCompleted.setRecycleItemId(ecoPointStandard.getStandardId()); //재활용품목  ID

            disposalService.editDisposal(disposalCompleted.getDisposalId(), point); //배출 내역에 포인트 저장

            List<Disposal> disposalItems = getDisposalListByUserId(request.getUserId()); //배출자의 배출 내역 조회
            
            // System.out.println(disposalItems.size() + "@@@@@@@@@@@@@size");
            
            disposalCompleted.publishAfterCommit();
            
            //포인트 적립
            if(ecoPointService.addEcoPoint(disposalCompleted).equals("Success")) {
                log.info("############## " + point + "  포인트 적립 Success ############## ");
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

    // @GetMapping("/{userId}")
    public List<Disposal> getDisposalListByUserId(Long userId) {
        return disposalService.getDisposalListByUserId(userId);
    }

}
