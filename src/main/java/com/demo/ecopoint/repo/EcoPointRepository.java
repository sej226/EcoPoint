package com.demo.ecopoint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.demo.ecopoint.domain.EcoPoint;

import java.util.*;


public interface EcoPointRepository extends JpaRepository<EcoPoint, Long> {
  EcoPoint findByMemberId(Long memberId);
  // EcoPoint findById(long id);
  // List<User> findAll();
}
