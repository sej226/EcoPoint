package com.demo.ecopoint.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.ecopoint.domain.DisposalCompleted;
import com.demo.ecopoint.domain.EcoPoint;
import com.demo.ecopoint.domain.PaymentCanceled;
import com.demo.ecopoint.domain.PaymentCompleted;
import com.demo.ecopoint.repo.EcoPointRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EcoPointService{
    @Autowired 
    EcoPointRepository ecoPointRepository;

    public String addEcoPoint(DisposalCompleted request){

      EcoPoint ecoPoint = ecoPointRepository.findByMemberId(request.getMemberId());
      if(ecoPoint != null){ //포인트가 기존에 있는 회원
        ecoPoint.setEcoPoint(request.getEcoPoint() +  ecoPoint.getEcoPoint());
        ecoPointRepository.save(ecoPoint);
      }
      else{   //포인트 처음 적립되는 회원
        ecoPointRepository.save(EcoPoint.builder()
                // .pointId(ecoPoint.getPointId())
                .ecoPoint(request.getEcoPoint())
                .memberId(request.getMemberId())
                .build());
      }

        return "Success";
    }


    public EcoPoint getEcoPointById(Long memberId) {
      EcoPoint ecoPoint = ecoPointRepository.findByMemberId(memberId);

      return ecoPoint;
    }

    public List<EcoPoint> getAllEcoPoint() {
      List<EcoPoint> ecoPointList = ecoPointRepository.findAll();
      return ecoPointList;
    }
    
    public String useEcoPoint(PaymentCompleted request) {
        EcoPoint ecoPoint = ecoPointRepository.findByMemberId(request.getMemberId());
        long point = ecoPoint.getEcoPoint();
        ecoPoint.setEcoPoint(point - request.getTotalPrice());
        ecoPoint.setMemberId(request.getMemberId());

        ecoPointRepository.save(ecoPoint);
        return "Success";
    }

    public String refoundPoint(PaymentCanceled request) {
      EcoPoint ecoPoint = ecoPointRepository.findByMemberId(request.getMemberId());
      long point = ecoPoint.getEcoPoint();
      ecoPoint.setEcoPoint(point + request.getTotalPrice());
      ecoPoint.setMemberId(request.getMemberId());

      ecoPointRepository.save(ecoPoint);
      return "Success";
    }

    
}
