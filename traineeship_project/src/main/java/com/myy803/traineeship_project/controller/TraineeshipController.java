package com.myy803.traineeship_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myy803.traineeship_project.domainmodel.CompanyEvaluation;
import com.myy803.traineeship_project.domainmodel.Interest;
import com.myy803.traineeship_project.domainmodel.ProfessorEvaluation;
import com.myy803.traineeship_project.domainmodel.Skill;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.service.CompanyService;
import com.myy803.traineeship_project.service.ProfessorService;
import com.myy803.traineeship_project.service.TraineeshipService;

@Controller
public class TraineeshipController {
	@Autowired
	private TraineeshipService service;
	@Autowired
	private CompanyService cService;
	@Autowired
	private ProfessorService pService;
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/announce-traineeship")
	public String retrieveTraineeship(Model model) {
		model.addAttribute("traineeship", new TraineeshipPosition());
		model.addAttribute("skills", Skill.values());
		model.addAttribute("interests", Interest.values());
		return "traineeship/new";
	}
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/save-traineeship")
	public String saveTraineeship(@ModelAttribute TraineeshipPosition traineeship) {
		service.saveTraineeship(traineeship);
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/available-traineeships")
	public String getAvailableTraineeships(Model model, @RequestParam(value="id", required=false) Integer id) {
		if (id != null) {
			model.addAttribute("traineeship", service.retrieveTraineeship(id));
			model.addAttribute("link", "/available-traineeships");
			return "traineeship/more-info";
		}
		model.addAttribute("traineeships", service.retrieveAvailableTraineeships());
		return "traineeship/available";
	}
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/assigned-traineeships")
	public String getAssignedTraineeships(Model model, @RequestParam(value="id", required=false) Integer id) {
		if (id != null) { 
			model.addAttribute("traineeship", service.retrieveTraineeship(id)); 
			model.addAttribute("link", "/assigned-traineeships"); 
			return "traineeship/more-info"; 
		}
		model.addAttribute("traineeships", service.retrieveAssignedTraineeships());
		return "traineeship/assigned";
	}
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/assigned-traineeships/evaluation")
	public String evaluateAssignedTraineeship(Model model, @RequestParam Integer id) {
		CompanyEvaluation evaluation = new CompanyEvaluation();
		evaluation.setPosition(service.retrieveTraineeship(id));
		model.addAttribute("evaluation", evaluation);
		return "company/evaluation";
	} 
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/save-company-evaluation")
	public String saveCompanyEvaluation(@ModelAttribute CompanyEvaluation evaluation) {
		cService.saveEvaluation(evaluation);
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('PROFESSOR')")
	@RequestMapping("/supervised-traineeships") 
	public String getSupervisedTraineeships(Model model, @RequestParam(value="id", required=false) Integer id) {
		if (id != null) {
			model.addAttribute("traineeship", service.retrieveTraineeship(id));
			model.addAttribute("link", "/supervised-traineeships");
			return "traineeship/more-info";
		}
		model.addAttribute("traineeships", service.retrieveSupervisedTraineeships());
		return "traineeship/supervised";
	}
	
	@PreAuthorize("hasAuthority('PROFESSOR')")
	@RequestMapping("/supervised-traineeships/evaluation")
	public String evaluateSupervisedTraineeship(Model model, @RequestParam Integer id) {
		ProfessorEvaluation evaluation = new ProfessorEvaluation();
		evaluation.setPosition(service.retrieveTraineeship(id));
		model.addAttribute("evaluation", evaluation);
		return "professor/evaluation";
	}
	
	@PreAuthorize("hasAuthority('PROFESSOR')")
	@RequestMapping("/save-professor-evaluation")
	public String saveProfessorEvaluation(@ModelAttribute ProfessorEvaluation evaluation) {
		pService.saveEvaluation(evaluation); 
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('COMPANY')")
	@RequestMapping("/delete-traineeship")
	public String deleteTraineeship(@RequestParam("traineeshipId") Integer traineeshipId) {
		service.deleteTraineeship(traineeshipId);
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('CMEMBER')")
	@RequestMapping("/in-progress-traineeships")
	public String retrieveInProgressTraineeships(Model model) {
		model.addAttribute("traineeships", service.retrieveInProgressTraineeships());
		return "traineeship/in-progress";
	}
	
	@PreAuthorize("hasAuthority('CMEMBER')")
	@RequestMapping("/in-progress-traineeships/assign")
	public String retrieveInProgressTraineeships(Model model, @RequestParam(value="id") Integer id, 
															  @RequestParam(value="strategy") String strategy) {

		boolean isAssigned = service.assignProfessor(id, strategy);
		if (!isAssigned) {
			model.addAttribute("message", "There are no professor matching your criteria.");
			model.addAttribute("traineeships", service.retrieveInProgressTraineeships());
			return "traineeship/in-progress";
		}
		
		return "homepage";
	}
	
	@PreAuthorize("hasAuthority('CMEMBER')")
	@RequestMapping("/in-progress-traineeships/evaluation")
	public String retrieveTraineeshipEvaluation(Model model, @RequestParam int id) {
		model.addAttribute("comp", service.retrieveTraineeship(id).getCompanyEvaluation());
		model.addAttribute("prof", service.retrieveTraineeship(id).getProfessorEvaluation());
		model.addAttribute("id", id);
		return "traineeship/evaluations";
	}
	
	@PreAuthorize("hasAuthority('CMEMBER')")
	@RequestMapping("/save-member-evaluation")
	public String saveTraineeshipEvaluation(@RequestParam int id, @RequestParam boolean grade) {
		service.savePassFailGrade(id, grade);
		
		return "homepage";
	}
}
