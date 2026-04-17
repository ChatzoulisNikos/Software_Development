package com.myy803.traineeship_project.domainmodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CompanyEvaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int motivation;
	
	private int effectiveness;
	
	private int efficiency;
	
	@OneToOne(mappedBy = "companyEvaluation")
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
	
	public void setPosition(TraineeshipPosition position) {
		this.position = position;
	}
}
