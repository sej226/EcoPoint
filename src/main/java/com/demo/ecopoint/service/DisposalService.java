package com.demo.ecopoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.demo.ecopoint.domain.Disposal;
import com.demo.ecopoint.repo.DisposalRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisposalService{

    private final DisposalRepository disposalRepository;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public Disposal disposalItem(Disposal request){
      Date now = new Date();
      String now_dt = format.format(now);
      System.out.println(now_dt); 

      return disposalRepository.save(Disposal.builder()
                .disposalProduct(request.getDisposalProduct())
                .quantity(request.getQuantity())
                .ecoPoint(request.getEcoPoint())
                .disposalDate(now_dt)
                .branchName(request.getBranchName())
                .userId(request.getUserId())
                // .member(request.getMember())
                .build());
        // return "Success";
    }


    public String deleteDisposal(Long disposalId) {
      Optional<Disposal> standard = disposalRepository.findById(disposalId);
      disposalRepository.delete(standard.get());
      return "Success";
   }

  public List<Disposal> getDisposalList() {
    List<Disposal> PointStandardList = disposalRepository.findAll();

    return PointStandardList;
  } 

  public List<Disposal> getDisposalListByUserId(Long userId) {
    List<Disposal> disposal = disposalRepository.findAllByUserId(userId);

    return disposal;
  }

  public String editDisposal(Long disposalId, Long ecoPoint) {
    Disposal disposal = disposalRepository.findByDisposalId(disposalId);
    System.out.println(disposalId  + " #################");
    System.out.println(disposal.getDisposalProduct() + " . " + disposal.getDisposalId());
    // disposal.get().setDisposalId(disposalId);
    if(disposal != null){
      disposal.setEcoPoint(ecoPoint);
      disposalRepository.save(disposal);

      return "Success";
    }
   
    return "Fail";
}
    
}
