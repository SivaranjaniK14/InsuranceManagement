package com.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer>{

}
