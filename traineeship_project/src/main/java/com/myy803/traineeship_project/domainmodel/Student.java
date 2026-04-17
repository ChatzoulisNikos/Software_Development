package com.myy803.traineeship_project.domainmodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String fullname;
	
	private String AM;
	
	private double avgGrade;
	
	private String preferredLocation;
	
	private String interests;
	
	private String skills;
	
	private boolean lookingForTraineeship = false;
	
	@OneToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
	private TraineeshipPosition assignedPosition;
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}
	
	public String getAM() {
		return AM;
	}
	
	public double getAvgGrade() {
		return avgGrade;
	}
	
	public String getPreferredLocation() {
		return preferredLocation;
	}
	
	public String getInterests() {
		return interests;
	}
	
	public String getSkills() {
		return skills;
	}
	
	public boolean getLookingForTraineeship() {
		return lookingForTraineeship;
	}
	
	public TraineeshipPosition getAssignedPosition() {
		return assignedPosition;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public void setAM(String AM) {
		this.AM = AM;
	}
	
	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public void setLookingForTraineeship(boolean lookingForTraineeship) {
		this.lookingForTraineeship = lookingForTraineeship;
	}
	
	public void setAssignedPosition(TraineeshipPosition assignedPosition) {
		this.assignedPosition = assignedPosition;
	}
	
}

