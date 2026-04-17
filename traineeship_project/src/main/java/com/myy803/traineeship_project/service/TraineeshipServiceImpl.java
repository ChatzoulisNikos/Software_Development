package com.myy803.traineeship_project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Company;
import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.Student;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.CompanyMapper;
import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;
import com.myy803.traineeship_project.strategies.PositionsSearchFactory;
import com.myy803.traineeship_project.strategies.PositionsSearchStrategy;
import com.myy803.traineeship_project.strategies.SupervisorAssignmentFactory;
import com.myy803.traineeship_project.strategies.SupervisorAssignmentStrategy;

@Service
public class TraineeshipServiceImpl implements TraineeshipService{
	@Autowired
	private TraineeshipMapper mapper;
	@Autowired
	private CompanyMapper compMapper;
	@Autowired
	private ProfessorMapper profMapper;
	@Autowired
	private StudentMapper stMapper;
	
	@Override
	public void saveTraineeship(TraineeshipPosition traineeship) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Company company = compMapper.findByUsername(username);
		traineeship.setCompany(company);
		mapper.save(traineeship);
	}

	@Override
	public List<TraineeshipPosition> retrieveAvailableTraineeships() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Company company = compMapper.findByUsername(username);
		List<TraineeshipPosition> traineeships = mapper.findAllByCompanyAndIsAssigned(company, false);
		return traineeships;
	}

	@Override
	public void deleteTraineeship(Integer traineeshipId) {
		mapper.deleteById(traineeshipId);
	}

	@Override
	public List<TraineeshipPosition> retrieveAssignedTraineeships() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Company company = compMapper.findByUsername(username);
		List<TraineeshipPosition> traineeships = mapper.findAllByCompanyAndIsAssigned(company, true);
		return traineeships;
	}

	@Override
	public TraineeshipPosition retrieveTraineeship(Integer id) {
		return mapper.findById(id).orElseThrow();
	}

	@Override
	public List<TraineeshipPosition> retrieveSupervisedTraineeships() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Professor professor = profMapper.findByUsername(username);
		System.out.println("Username: " + username);
		
		if (professor != null)
			return mapper.findAllByProfessor(professor);
		else 
			return null;
	}

	@Override
	public List<TraineeshipPosition> retrieveTraineeshipsForApplicant(String strategy, Integer id) {
		PositionsSearchFactory factory = new PositionsSearchFactory();
		PositionsSearchStrategy str = factory.create(strategy);
		return str.search(id, mapper, stMapper);
	}

	@Override
	public void assignTraineeship(Integer userId, Integer trainId) {
		Student student = stMapper.findById(userId).orElseThrow();
		TraineeshipPosition traineeship = mapper.findById(trainId).orElseThrow();
		traineeship.setIsAssigned(true);
		traineeship.setStudent(student);
		student.setAssignedPosition(traineeship);
		stMapper.save(student);
	}

	@Override
	public List<TraineeshipPosition> retrieveInProgressTraineeships() {
		
		return mapper.findAllByFromDateBeforeAndToDateAfter(LocalDate.now(), LocalDate.now());
	}

	@Override
	public boolean assignProfessor(int id, String strategyString) {
		SupervisorAssignmentFactory factory = new SupervisorAssignmentFactory();
		SupervisorAssignmentStrategy strategy = factory.create(strategyString);
		
		strategy.assign(id, mapper, profMapper);
		
		TraineeshipPosition position = mapper.findById(id).get();
		if (position.getProfessor() != null)
			return true;
		
		return false;
	}
	
	@Override
	public void savePassFailGrade(int id, boolean grade) {
		TraineeshipPosition position = mapper.findById(id).orElseThrow();
		position.setPassFailGrade(grade);
		mapper.save(position);
	}
 	
	
	
	
	
	
	
	
	
	
}
