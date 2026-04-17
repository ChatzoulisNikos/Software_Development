package com.myy803.traineeship_project.domainmodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ProfessorEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int motivation;
	
	private int effectiveness;
	
	private int efficiency;
	
	private int facilities;
	
	private int guidance;

	@OneToOne(mappedBy = "professorEvaluation")
	private TraineeshipPosition position;
	
	
	public int getId() {
		return id;
	}

	public int getMotivation() {
		return motivation;
	}

	public int getEffectiveness() {
		return effectiveness;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public int getFacilities() {
		return facilities;
	}

	public int getGuidance() {
		return guidance;
	}
	
	public TraineeshipPosition getPosition() {
		return position;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public void setFacilities(int facilities) {
		this.facilities = facilities;
	}

	public void setGuidance(int guidance) {
		this.guidance = guidance;
	}
	
	public void setPosition(TraineeshipPosition position) {
		this.position = position;
	}
}
