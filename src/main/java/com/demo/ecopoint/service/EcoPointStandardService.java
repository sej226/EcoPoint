package com.demo.ecopoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.demo.ecopoint.domain.EcoPointStandard;
import com.demo.ecopoint.repo.PointStandardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EcoPointStandardService{

    private final PointStandardRepository pointStandardRepository;

    public String addPointStandard(EcoPointStandard request){
      pointStandardRepository.save(EcoPointStandard.builder()
                .classification(request.getClassification())
                .countweightstandard(request.getCountweightstandard())
                .standardDesc(request.getStandardDesc())
                .ecoPoint(request.getEcoPoint())
                .build());

        return "Success";
    }


    public List<EcoPointStandard> getEcoPointStandardList() {
      List<EcoPointStandard> PointStandardList = pointStandardRepository.findAll();

      return PointStandardList;
  }

  public EcoPointStandard getEcoPointStandarByClassification(String classification) {
    EcoPointStandard pointStandard = pointStandardRepository.findByClassification(classification);
    System.out.println(pointStandard.getClassification() + "  : getEcoPointStandarByProduct");
    return pointStandard;
  }

  public EcoPointStandard getEcoPointStandarById(long id) {
    EcoPointStandard pointStandard = pointStandardRepository.findById(id);
    System.out.println(pointStandard.getClassification() + "  : getEcoPointStandarById");
    return pointStandard;
  }

  public String editEcoPointStandard(Long standardId, EcoPointStandard request) {
      Optional<EcoPointStandard> standard = pointStandardRepository.findById(standardId);

      standard.get().setClassification(request.getClassification());
      standard.get().setCountweightstandard(request.getCountweightstandard());
      standard.get().setEcoPoint(request.getEcoPoint());
      standard.get().setStandardDesc(request.getStandardDesc());
      // System.out.println(request.getEcoPoint() + " !!!!!!!!!!!!!!!!!!!!!!");
 
      pointStandardRepository.save(standard.get());
      return "Success";
  }

   public String deleteEcoPointStandard(Long standardId) {
      Optional<EcoPointStandard> standard = pointStandardRepository.findById(standardId);
      pointStandardRepository.delete(standard.get());
      return "Success";
  }
    
}
