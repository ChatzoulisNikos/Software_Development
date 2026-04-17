package com.myy803.traineeship_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myy803.traineeship_project.domainmodel.Company;
import com.myy803.traineeship_project.service.CompanyService;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService service; 
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/company-info")
	public String retrieveProfile(Model model) {
		
		model.addAttribute("company", new Company());
		
		return "company/info";
	}
	 
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/company-save")
	public String saveProfile(@ModelAttribute("company") Company company) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		company.setUsername(username);
		service.saveProfile(company);
		
		return "homepage";
	}
}
