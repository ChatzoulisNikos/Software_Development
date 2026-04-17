package com.myy803.traineeship_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Student;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentMapper mapper;
	@Autowired
	TraineeshipMapper tMapper;
	
	@Override
	public void saveProfile(Student student) {
		if (mapper.findByUsername(student.getUsername()) == null) {
			mapper.save(student);
		}
	}

	@Override
	public void applyForTraineeship() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = mapper.findByUsername(username);
		
		if (student !=null) {
			student.setLookingForTraineeship(true);
			mapper.save(student);
		}
	}

	@Override
	public List<Student> retrieveAppliedUsers() {
		return mapper.findAllByLookingForTraineeship(true);
	}

	@Override
	public void saveLogbook(String logbook) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Student student = mapper.findByUsername(username);
		
		student.getAssignedPosition().setStudentLogbook(logbook);
		tMapper.save(student.getAssignedPosition());
	}

}
