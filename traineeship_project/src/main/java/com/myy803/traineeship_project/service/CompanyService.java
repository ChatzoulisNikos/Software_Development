package com.myy803.traineeship_project.service;

import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Company;
import com.myy803.traineeship_project.domainmodel.CompanyEvaluation;

@Service
public interface CompanyService {

	public void saveProfile(Company company);

	void saveEvaluation(CompanyEvaluation companyEvaluation);
}
