package com.demo.ecopoint.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecopoint.domain.DisposalCompleted;
import com.demo.ecopoint.domain.PaymentCanceled;
import com.demo.ecopoint.domain.PaymentCompleted;
import com.demo.ecopoint.service.EcoPointService;


@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("ecoPoint")
@RequiredArgsConstructor
public class EcoPointController {
 
    private final EcoPointService ecoPointService;
    
    //포인트 부여
    @PostMapping("/reward")
    public ResponseEntity addEcoPoint(@RequestBody DisposalCompleted request) {
        log.info("ecoPoint = {}, memberId = {}, Id = {} "
                 , request.getEcoPoint(), request.getMemberId(), request.getDisposalId());

        if(ecoPointService.addEcoPoint(request).equals("Success")) {
            System.out.println("####### addEcoPoint Success #######");
            return new ResponseEntity(HttpStatus.CREATED);
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/{memberId}")
    public ResponseEntity getEcoPointList(@PathVariable Long memberId) {
        System.out.println(memberId + "############################");
        return ResponseEntity.ok().body(ecoPointService.getEcoPointById(memberId));
    }

    @GetMapping
    public ResponseEntity getEcoPointAllList() {
        return ResponseEntity.ok().body(ecoPointService.getAllEcoPoint());
    }

    //포인트 차감
    @PostMapping("/useEcoPoint")
    public ResponseEntity useEcoPoint( @RequestBody PaymentCompleted request ) {
        log.info("useEcoPoint ", request.getMemberId(), request.getPaymentMethod());

        if(ecoPointService.useEcoPoint(request).equals("Success")) {
            System.out.println("####### useEcoPoint Success #######");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //포인트 환불
    @PostMapping("/refoundPoint")
    public ResponseEntity refoundPoint( @RequestBody PaymentCanceled request ) {
        log.info("update ", request.getMemberId(), request.getPaymentMethod());

        if(ecoPointService.refoundPoint(request).equals("Success")) {
            System.out.println("####### refoundPoint Success #######");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

} 

