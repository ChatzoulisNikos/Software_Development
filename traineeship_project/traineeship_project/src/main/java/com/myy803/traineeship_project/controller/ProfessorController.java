package com.myy803.traineeship_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myy803.traineeship_project.domainmodel.Interest;
import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.service.ProfessorService;

@Controller
public class ProfessorController {

	@Autowired
	ProfessorService service;
	
	@PreAuthorize("hasAuthority('PROFESSOR')")
	@RequestMapping("/professor-info")
	public String retrieveProfile(Model model) {
		
		model.addAttribute("professor", new Professor());
		model.addAttribute("interests", Interest.values());
		
		return "professor/info";
	}
	
	@PreAuthorize("hasAuthority('PROFESSOR')")
	@RequestMapping("/professor-save")
	public String saveProfile(@ModelAttribute("professor") Professor professor) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		professor.setUsername(username);
		service.saveProfile(professor);
		
		return "redirect:/";
	}
}
