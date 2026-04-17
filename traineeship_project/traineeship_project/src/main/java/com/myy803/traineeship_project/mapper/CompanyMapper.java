package com.myy803.traineeship_project.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.traineeship_project.domainmodel.Company;

public interface CompanyMapper extends JpaRepository<Company, Integer> {

	Company findByUsername(String username);

}
