package com.myy803.traineeship_project.domainmodel;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;


import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="traineeshipPositions")
public class TraineeshipPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	private String description;
	
	private LocalDate fromDate;
	 
	private LocalDate toDate;

	private String topics;
	
	private String skills;

	private boolean isAssigned;
	
	private String studentLogbook;
	
	private Boolean passFailGrade = null;
	
	@OneToOne(mappedBy = "assignedPosition")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "professor_id")
	private Professor professor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
	private Company company;

	@OneToOne
	@JoinColumn(name = "company_eval_id", referencedColumnName = "id")
	private CompanyEvaluation companyEvaluation;
	
	@OneToOne
	@JoinColumn(name = "professor_eval_id", referencedColumnName = "id")
	private ProfessorEvaluation professorEvaluation;
	
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public String getTopics() {
		return topics;
	}

	public String getSkills() {
		return skills;
	}

	public boolean getIsAssigned() {
		return isAssigned;
	}

	public String getStudentLogbook() {
		return studentLogbook;
	}

	public boolean getIsPassFailGrade() {
		return passFailGrade;
	}

	public Student getStudent() {
		return student;
	}

	public Professor getProfessor() {
		return professor;
	}

	public Company getCompany() {
		return company;
	}

	public CompanyEvaluation getCompanyEvaluation() {
		return companyEvaluation;
	}
	
	public ProfessorEvaluation getProfessorEvaluation() {
		return professorEvaluation;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setIsAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public void setStudentLogbook(String studentLogbook) {
		this.studentLogbook = studentLogbook;
	}

	public void setPassFailGrade(boolean passFailGrade) {
		this.passFailGrade = passFailGrade;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSupervisor(Professor professor) {
		this.professor = professor;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setCompanyEvaluation(CompanyEvaluation companyEvaluation) {
		this.companyEvaluation = companyEvaluation;
	}
	
	public void setProfessorEvaluation(ProfessorEvaluation professorEvaluation) {
		this.professorEvaluation = professorEvaluation;
	}
}

