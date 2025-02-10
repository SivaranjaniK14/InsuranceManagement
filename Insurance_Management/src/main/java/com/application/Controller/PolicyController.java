package com.application.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.Exception.ClientNotFoundException;
import com.application.Exception.PolicyNotFoundException;
import com.application.Model.InsurancePolicy;
import com.application.Service.InsurancePolicyServices;

@RestController
@RequestMapping("/api")
public class PolicyController {
	@Autowired
	private InsurancePolicyServices policyService;

	
	@GetMapping("/policies")
	public ResponseEntity<List<InsurancePolicy>> getClients() throws PolicyNotFoundException {
		List<InsurancePolicy> found=policyService.getPolicies();
		return new ResponseEntity<>(found,HttpStatus.FOUND);
	}
	
	@GetMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> getClient(@PathVariable("id") Integer id) throws PolicyNotFoundException{
		InsurancePolicy found=policyService.getPolicy(id);
		return new ResponseEntity<>(found,HttpStatus.FOUND);
	}
	
	@PostMapping("/policies")
	public ResponseEntity<InsurancePolicy> newClient(@Valid @RequestBody InsurancePolicy P,@RequestParam Integer Cid) throws ClientNotFoundException{
		InsurancePolicy saved=policyService.newPolicy(P,Cid);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@PutMapping("/policies/{id}")
	public ResponseEntity<InsurancePolicy> updateClient(@Valid @RequestBody InsurancePolicy p,@PathVariable("id")Integer id) throws PolicyNotFoundException {
		InsurancePolicy saved=policyService.updatePolicy(id,p);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id")Integer id) throws PolicyNotFoundException {
		policyService.deletePolicy(id);
		return new ResponseEntity<>("Deleted SuccessFully",HttpStatus.GONE);
	}
	
	@GetMapping("/policies/view")
	public String viewPolicies(Model model) {
	    try {
	        List<InsurancePolicy> policies = policyService.getPolicies();
	        model.addAttribute("policies", policies);
	        model.addAttribute("title", "Policies List");
	        model.addAttribute("content", "policies :: content");
	    } catch (PolicyNotFoundException e) {
	        model.addAttribute("error", "Policies not found");
	    }
	    return "index";
	}
}
