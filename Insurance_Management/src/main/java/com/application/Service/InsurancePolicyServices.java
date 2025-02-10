package com.application.Service;

import java.util.List;

import com.application.Exception.ClientNotFoundException;
import com.application.Exception.PolicyNotFoundException;
import com.application.Model.InsurancePolicy;

public interface InsurancePolicyServices {
	public List<InsurancePolicy> getPolicies()throws PolicyNotFoundException;
	public InsurancePolicy getPolicy(Integer Id)throws PolicyNotFoundException;
	public InsurancePolicy newPolicy(InsurancePolicy p,Integer c)throws ClientNotFoundException;
	public InsurancePolicy updatePolicy(Integer Id,InsurancePolicy p)throws PolicyNotFoundException;
	public void deletePolicy(Integer Id)throws PolicyNotFoundException;
}
