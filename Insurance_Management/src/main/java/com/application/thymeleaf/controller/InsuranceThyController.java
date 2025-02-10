package com.application.thymeleaf.controller;

import com.application.Exception.ClientNotFoundException;
import com.application.Exception.PolicyNotFoundException;
import com.application.Model.Client;
import com.application.Model.InsurancePolicy;
import com.application.Repository.ClientRepository;
import com.application.Service.InsurancePolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/policies")
public class InsuranceThyController {

  @Autowired
  private InsurancePolicyServiceImpl policyService;

  @Autowired
  private ClientRepository clientRepo;

  // Display all policies

  @GetMapping("/view")
  public String viewPolicies(Model model) {
    try {
		model.addAttribute("policies", policyService.getPolicies());
	} catch (PolicyNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return "viewPolicies";

  }

  // Show the form to add a new policy

  @GetMapping("/new")
  public String newPolicyForm(Model model) {
    model.addAttribute("policy", new InsurancePolicy());
    model.addAttribute("clients", clientRepo.findAll());
    return "policyForm";

  }

  // Show the form to edit an existing policy

  @GetMapping("/edit/{id}")
  public String editPolicyForm(@PathVariable Integer id, Model model) {
    try {
		model.addAttribute("policy", policyService.getPolicy(id));
	} catch (PolicyNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    model.addAttribute("clients", clientRepo.findAll());
    return "policyForm";

  }

  // Save a new or updated policy

  @PostMapping("/save")
  public String savePolicy(@ModelAttribute InsurancePolicy policy, @RequestParam Integer policyHolderId) {

    Client client = clientRepo.findById(policyHolderId).orElse(null);
    policy.setPolicyHolder(client);
    try {
		policyService.newPolicy(policy, policyHolderId);
	} catch (ClientNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return "redirect:/policies/view";

  }

  // Delete a policy

  @GetMapping("/delete/{id}")
  public String deletePolicy(@PathVariable Integer id) {
    try {
		policyService.deletePolicy(id);
	} catch (PolicyNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return "redirect:/policies/view";

  }

}