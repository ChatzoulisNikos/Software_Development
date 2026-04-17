package com.myy803.traineeship_project.domainmodel;

public enum Role {
	STUDENT("STUDENT"),
    COMPANY("COMPANY"),
	PROFESSOR("PROFESSOR"),
	CMEMBER("CMEMBER");
	
    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
