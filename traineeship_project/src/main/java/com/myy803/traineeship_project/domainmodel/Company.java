package com.myy803.traineeship_project.domainmodel;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	private String username;
	
	private String companyName;
	
	private String location;
	
	@OneToMany(mappedBy = "company")
	private List<TraineeshipPosition> positions;

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getLocation() {
		return location;
	}

	public List<TraineeshipPosition> getPositions() {
		return positions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPositions(List<TraineeshipPosition> positions) {
		this.positions = positions;
	}
	
}
