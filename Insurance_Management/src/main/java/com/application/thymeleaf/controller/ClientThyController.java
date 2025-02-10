package com.application.thymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.Exception.ClientNotFoundException;
import com.application.Model.Client;
import com.application.Service.ClientServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ims/clients")
public class ClientThyController {

	@Autowired
	ClientServiceImpl clientService;

	@GetMapping("/login")

	public String showLoginForm(Model model) {
		model.addAttribute("client", new Client());
		return "login";
	}

	@PostMapping("/login")
	public String loginClient(@ModelAttribute("client") Client client, Model model) {

		boolean isAuthenticated = clientService.authenticate(client);
		
		if (isAuthenticated) {
//			model.addAttribute("message", "Login successful!");
			return "redirect:/"; // Redirect to dashboard page after login
		} else {
			model.addAttribute("error", "Invalid User Name or password.");
			return "login";
		}
	}

	@GetMapping("/clients/view")
	public String viewClients(Model model) {
		try {
			List<Client> clients = clientService.getClients();
			model.addAttribute("clients", clients);
			model.addAttribute("title", "Clients List");
			model.addAttribute("content", "clients :: content");
		} catch (ClientNotFoundException e) {
			model.addAttribute("error", "Clients not found");
		}
		return "index";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("client", new Client());
		return "register";
	}

	@PostMapping("/register")
	public String registerClient(@Valid @ModelAttribute("client") Client client, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		clientService.newClient(client);
		return "redirect:/ims/clients/login";
	}

}
