package com.myy803.traineeship_project.domainmodel;

public enum Skill {
	JAVA("JAVA"),
	PYTHON("PYTHON"),
	C("C"),
	C_PLUS_PLUS("C++"),
	SQL("SQL"),
	HTML_CSS("HTML & CSS"),
	JAVASCRIPT("JAVASCRIPT"),
	SPRING_BOOT("SPRING BOOT"),
	REACT("REACT"),
	ANGULAR("ANGULAR"),
	GIT("GIT"),
	PROBLEM_SOLVING("PROBLEM SOLVING"),
	TEAMWORK("TEAMWORK"),
	COMMUNICATION("COMMUNICATION"),
	TIME_MANAGEMENT("TIME MANAGEMENT"),
	ADAPTABILITY("ADAPTABILITY"),
	CRITICAL_THINKING("CRITICAL THINKING"),
	DEBUGGING("DEBUGGING"),
	UNIT_TESTING("UNIT TESTING"),
	CLOUD_COMPUTING("CLOUD COMPUTING"),
	DOCKER("DOCKER"),
	CI_CD("CI CD"),
	REST_APIS("REST APIS");

	private final String value;
	
	private Skill(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
