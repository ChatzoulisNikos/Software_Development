package com.myy803.traineeship_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.myy803.traineeship_project.controller.ProfessorController;
import com.myy803.traineeship_project.controller.TraineeshipController;
import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.mapper.CompanyMapper;
import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;
import com.myy803.traineeship_project.service.ProfessorService;
import com.myy803.traineeship_project.service.TraineeshipServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TraineeshipProjectApplicationTests {
    @Mock
    private ProfessorService professorService;
    @Mock
    private TraineeshipServiceImpl traineeshipService;
    @Mock
    private Model model;
    @InjectMocks
    private TraineeshipController traineeshipController;
    @InjectMocks
    private ProfessorController professorController;
    @Mock
    private ProfessorMapper professorMapper;
    @Mock
    private TraineeshipMapper traineeshipMapper;
    @Mock
    private CompanyMapper companyMapper;

    
    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	void contextLoads() {
	}
	
    @Test
    public void testShowSupervised() {
        String viewName = traineeshipController.getSupervisedTraineeships(model, null);
        assertEquals("traineeship/supervised", viewName);
    }
    
    @Test
    public void testSaveProfessor() {
    	Authentication authentication = mock(Authentication.class);
    	when(authentication.getName()).thenReturn("testuser");
    	SecurityContext securityContext = mock(SecurityContext.class);
    	when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    	
        Professor professor = new Professor();
        String viewName = professorController.saveProfile(professor);
        assertEquals("redirect:/", viewName);
        verify(professorService, times(1)).saveProfile(professor);
        
        SecurityContextHolder.clearContext();
    }
    
    @Test
    public void testSaveProfileWithoutAuthentication() {
        Professor professor = new Professor();

        assertThrows(NullPointerException.class, () -> {
            professorController.saveProfile(professor);
        });
    }
        
    @Test
    public void testDeletePosition() {
        int id = -1;

        String viewName = traineeshipController.deleteTraineeship(id);

        assertEquals("homepage", viewName);
        verify(traineeshipService, times(1)).deleteTraineeship(id);
    }

 

}
