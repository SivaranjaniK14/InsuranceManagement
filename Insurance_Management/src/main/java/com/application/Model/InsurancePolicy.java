package com.application.Model;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity
@Data
public class InsurancePolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_seq")
	@SequenceGenerator(name = "insurance_seq", sequenceName = "insurance_seq", allocationSize = 1)
	private Integer policyId;
	private PolicyType type;
	@Min(value=10000)
	private double coverageAmount;
	@Min(value=100)
	private double premium;
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date startDate=new Date();
	@Future
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date endDate;
	
	@ManyToOne
	@JsonProperty(access = Access.READ_ONLY)
	private Client policyHolder;
	
//	@OneToOne
//	@JsonProperty(access = Access.READ_ONLY)
//	private Claim claim;
}
