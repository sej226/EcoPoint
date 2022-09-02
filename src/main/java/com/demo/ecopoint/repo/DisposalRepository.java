package com.demo.ecopoint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.ecopoint.domain.Disposal;

import java.util.*;

// @RepositoryRestResource
public interface DisposalRepository extends JpaRepository<Disposal, Long> {
  Disposal findByDisposalId(Long disposalId);
  // EcoPoint findById(long id);
  // List<User> findAll();
}
