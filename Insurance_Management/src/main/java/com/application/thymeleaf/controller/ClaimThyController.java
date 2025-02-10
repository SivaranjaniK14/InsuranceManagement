
package com.application.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.Exception.ClaimNotFoundException;
import com.application.Exception.PolicyNotFoundException;
import com.application.Model.Claim;
import com.application.Service.ClaimServices;

@Controller

@RequestMapping("/claims")

public class ClaimThyController {
	@Autowired

	  private ClaimServices claimService;

	  @GetMapping("")

	  public String viewClaims(Model model) {

	    try { 

	      List<Claim> claims = claimService.getClaims();

	      model.addAttribute("claims", claims);

	    } catch (ClaimNotFoundException e) {

	      model.addAttribute("error", "Claims not found");

	    }

	    return "claims"; // Thymeleaf template

	  }

	  @GetMapping("/new")

	  public String showAddClaimForm(Model model) {

	    model.addAttribute("claim", new Claim());

	    return "claim_form";

	  }

	  @GetMapping("/edit/{id}")

	  public String showEditClaimForm(@PathVariable("id") Integer id, Model model) {

	    try {

	      Claim claim = claimService.getClaim(id);

	      model.addAttribute("claim", claim);

	      return "claim_form";

	    } catch (ClaimNotFoundException e) {

	      return "redirect:/claims";

	    }

	  }

	  @PostMapping("/save")

	  public String saveClaim(@ModelAttribute Claim claim) throws PolicyNotFoundException {

	    claimService.newClaim(claim, claim.getPolicy().getPolicyId());

	    return "redirect:/claims";

	  }

	  @GetMapping("/delete/{id}")

	  public String deleteClaim(@PathVariable("id") Integer id) {

	    try {

	      claimService.deleteClaim(id);

	    } catch (ClaimNotFoundException e) {

	      // Handle exception

	    }

	    return "redirect:/claims";

	  }
}

