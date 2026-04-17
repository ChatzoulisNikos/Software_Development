package com.myy803.traineeship_project.domainmodel;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private String username;
	
	private String fullname;
	
	private String interests;
	
	@OneToMany(mappedBy = "professor")
	List<TraineeshipPosition> supervisedPositions;

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public String getInterests() {
		return interests;
	}
	
	public List<TraineeshipPosition> getSupervisedPositions(){
		return supervisedPositions;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
		this.supervisedPositions = supervisedPositions;
	}
}
