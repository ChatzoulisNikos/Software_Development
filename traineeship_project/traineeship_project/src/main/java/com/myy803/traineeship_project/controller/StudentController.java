package com.myy803.traineeship_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myy803.traineeship_project.domainmodel.Interest;
import com.myy803.traineeship_project.domainmodel.Skill;
import com.myy803.traineeship_project.domainmodel.Student;
import com.myy803.traineeship_project.service.StudentService;
import com.myy803.traineeship_project.service.TraineeshipService;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	@Autowired
	private TraineeshipService trainService;
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@RequestMapping("/student-info")
	public String retrieveProfile(Model model) {
		
		model.addAttribute("student", new Student());
		model.addAttribute("skills", Skill.values());
		model.addAttribute("interests", Interest.values());
		
		return "student/info";
	}
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@RequestMapping("/student-save")
	public String saveProfile(@ModelAttribute("student") Student student) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		student.setUsername(username);
		
		service.saveProfile(student);
		
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@RequestMapping("/apply-for-position")
	public String applyForTraineeship() {
		service.applyForTraineeship();
		
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('CMEMBER')")
	@RequestMapping("/applied-users")
	public String retrieveAppliedUsers(Model model, @RequestParam(value="id", required=false) Integer id, 
													@RequestParam(value="strategy", required=false) String strategy, 
													@RequestParam(value="traineeshipId", required=false) Integer trainId) {
		if (id != null && trainId == null) {
			model.addAttribute("traineeships", trainService.retrieveTraineeshipsForApplicant(strategy, id));
			model.addAttribute("userId", id);
			return "student/traineeships";
		}
		if (id != null && trainId != null) {
			trainService.assignTraineeship(id, trainId);
			return "homepage";
		}
		model.addAttribute("users", service.retrieveAppliedUsers());
		return "student/applied";
	}
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@RequestMapping("/fill-logbook")
	public String fillLogbook(Model model) {
		model.addAttribute("logbook", new String());
		return "student/logbook";
	}
	
	@PreAuthorize("hasAuthority('STUDENT')")
	@RequestMapping("/fill-logbook/save")
	public String saveLogbook(@ModelAttribute("logbook") String logbook) {
		service.saveLogbook(logbook);
		return "homepage";
	}
}
