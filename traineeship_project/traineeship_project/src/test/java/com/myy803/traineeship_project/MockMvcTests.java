package com.myy803.traineeship_project;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.myy803.traineeship_project.service.CompanyService;
import com.myy803.traineeship_project.service.ProfessorService;
import com.myy803.traineeship_project.service.TraineeshipService;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TraineeshipService service;
    @MockBean
    private CompanyService cService;
    @MockBean
    private ProfessorService pService;

    @Test
    public void testGetAvailableTraineeships() throws Exception {
    	when(service.retrieveAvailableTraineeships()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/available-traineeships")
        		.with(user("testuser").authorities(new SimpleGrantedAuthority("COMPANY"))))
               .andExpect(status().isOk());
    }   
    
    @Test
    public void testGetAutorizationError() throws Exception {
    	mockMvc.perform(get("/supervised-traineeships")
                .with(user("testuser").authorities(new SimpleGrantedAuthority("STUDENT"))))
                .andExpect(status().isForbidden());
    }
}
