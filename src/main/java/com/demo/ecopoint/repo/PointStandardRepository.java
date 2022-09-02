package com.demo.ecopoint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.demo.ecopoint.domain.EcoPointStandard;

import java.util.*;

// @CrossOrigin("http://localhost:3000")
// @RepositoryRestResource(excerptProjection = WithWriterProjection.class)
public interface PointStandardRepository extends JpaRepository<EcoPointStandard, Long> {
  EcoPointStandard findByClassification(String classification);
  EcoPointStandard findById(long id);
  // List<User> findAll();
}
