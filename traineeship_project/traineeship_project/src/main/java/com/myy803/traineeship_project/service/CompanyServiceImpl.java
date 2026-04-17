package com.myy803.traineeship_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Company;
import com.myy803.traineeship_project.domainmodel.CompanyEvaluation;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.CompanyEvaluationMapper;
import com.myy803.traineeship_project.mapper.CompanyMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyMapper mapper;
	
	@Autowired
	CompanyEvaluationMapper evMapper;
	@Autowired
	TraineeshipMapper tMapper;
	
	@Override
	public void saveProfile(Company company) {
		if (mapper.findByUsername(company.getUsername()) == null) {
			mapper.save(company);
		}
	}
	
	@Override
	public void saveEvaluation(CompanyEvaluation companyEvaluation) {
		TraineeshipPosition position = companyEvaluation.getPosition();
		position.setCompanyEvaluation(companyEvaluation);
		evMapper.save(companyEvaluation);
		tMapper.save(position);
	}

}
