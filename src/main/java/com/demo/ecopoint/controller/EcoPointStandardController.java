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

import com.demo.ecopoint.domain.EcoPointStandard;
import com.demo.ecopoint.service.EcoPointStandardService;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("ecoPointStandard")
@RequiredArgsConstructor
public class EcoPointStandardController {
 
    private final EcoPointStandardService ecoPointStandardService;
    
    @PostMapping("/addStandard")
    public ResponseEntity addPointStandard(@RequestBody EcoPointStandard request) {
      //
        log.info("classification = {}, countweightstandard = {}, standardDesc = {}, ecoPoint = {}"
        , request.getClassification(), request.getCountweightstandard(), request.getStandardDesc(), request.getEcoPoint());

        if(ecoPointStandardService.addPointStandard(request).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping
    public ResponseEntity getEcoPointStandardList() {
        return ResponseEntity.ok().body(ecoPointStandardService.getEcoPointStandardList());
    }

    //@RequestParam("classification")
    @GetMapping("/RequestParam")
    public ResponseEntity getEcoPointStandarByClassification(
        @RequestParam(required = false, value = "classification") String classification)   {
    // @PathVariable String classification) {

        System.out.println( "getEcoPointStandarByClassification input: " + classification + "/" );
        return ResponseEntity.ok().body(ecoPointStandardService.getEcoPointStandarByClassification(classification));
    }

    @PatchMapping("/{standardId}")
    // @PostMapping("/{standardId}")
    public ResponseEntity postUpdate( @PathVariable Long standardId, @RequestBody EcoPointStandard request ) {
        log.info("standardId = {}", standardId);
        log.info("update Classification = {}, update EcoPoint = {}", request.getClassification(), request.getEcoPoint());
        if(ecoPointStandardService.editEcoPointStandard(standardId, request).equals("Success")) {
          log.info("########## editEcoPointStandard ##########");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{standardId}")
    public ResponseEntity deleteEcoPointStandard(@PathVariable Long standardId) {
        log.info("postId = {}", standardId);
        if(ecoPointStandardService.deleteEcoPointStandard(standardId).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

} 

