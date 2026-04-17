package com.myy803.traineeship_project.mapper;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.traineeship_project.domainmodel.Company;
import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;

public interface TraineeshipMapper extends JpaRepository<TraineeshipPosition, Integer>{

	List<TraineeshipPosition> findAllByCompanyAndIsAssigned(Company company, boolean isAssigned);

	List<TraineeshipPosition> findAllByProfessor(Professor professor);

	List<TraineeshipPosition> findAllByIsAssigned(boolean b);
	
	List<TraineeshipPosition> findAllByFromDateBeforeAndToDateAfter(LocalDate today1, LocalDate today2);

}
