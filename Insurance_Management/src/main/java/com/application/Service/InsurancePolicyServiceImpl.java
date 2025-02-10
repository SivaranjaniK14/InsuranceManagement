package com.application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Exception.ClientNotFoundException;
import com.application.Exception.PolicyNotFoundException;
import com.application.Model.Client;
import com.application.Model.InsurancePolicy;
import com.application.Repository.ClientRepository;
import com.application.Repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyServices{
	
	@Autowired
	private InsurancePolicyRepository policyRepo;
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public List<InsurancePolicy> getPolicies() throws PolicyNotFoundException {
		List<InsurancePolicy> list=policyRepo.findAll();
		if(list.size()==0) {
			throw new PolicyNotFoundException("Policy Not Found!");
		}
		return list;
	}

	@Override
	public InsurancePolicy getPolicy(Integer Id) throws PolicyNotFoundException {
		InsurancePolicy found=policyRepo.findById(Id).orElseThrow(()-> new PolicyNotFoundException("Policy Not Found!"));
		return found;
	}

	@Override
	public InsurancePolicy newPolicy(InsurancePolicy p,Integer cid) throws ClientNotFoundException {
		Client found=clientRepo.findById(cid).orElseThrow(()-> new ClientNotFoundException("Client Not Found!"));
		p.setPolicyHolder(found);
		return policyRepo.save(p);
	}

	@Override
	public InsurancePolicy updatePolicy(Integer Id,InsurancePolicy p) throws PolicyNotFoundException {
		InsurancePolicy found=policyRepo.findById(Id).orElseThrow(()-> new PolicyNotFoundException("Policy Not Found!"));
		found.setPremium(p.getPremium());
		found.setCoverageAmount(p.getCoverageAmount());
		return policyRepo.save(found);
	}

	@Override
	public void deletePolicy(Integer Id) throws PolicyNotFoundException {
		InsurancePolicy found=policyRepo.findById(Id).orElseThrow(()-> new PolicyNotFoundException("Policy Not Found!"));
		policyRepo.delete(found);
	}

	

}
