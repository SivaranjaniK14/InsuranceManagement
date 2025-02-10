package com.application.Model;
import java.util.Date;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@Table(name = "claim")
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_seq")
	@SequenceGenerator(name = "claim_seq", sequenceName = "claim_seq", allocationSize = 1)
	private Integer claimId;
	
	private String description;
	
	@NotNull(message="Claim Date cannot be null")
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Data claimDate;
	@JsonProperty(access = Access.READ_ONLY)
	private ClaimStatus claimStatus;
	
	@OneToOne
	@JsonProperty(access = Access.READ_ONLY) 
	private InsurancePolicy policy;

//	public String getDescription() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
