package com.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer>{

}