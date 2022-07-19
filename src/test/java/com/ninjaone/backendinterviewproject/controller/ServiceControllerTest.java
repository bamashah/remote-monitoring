package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.model.Svc;
import com.ninjaone.backendinterviewproject.service.SvcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(SvcController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ServiceControllerTest {
    public static final String ID = "BACKUP";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private SvcService serviceService;

    private Svc service;

    @BeforeEach
    void setup(){
        service = new Svc(ID, 4L, "Back up");
    }

    @Test
    void testGetService() throws Exception {
        when(serviceService.getSvc(ID)).thenReturn(Optional.of(service));

        mockMvc.perform(get("/service/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(service)));
    }

    @Test
    void testCreateService() throws Exception {
        when(serviceService.createSvc(any())).thenReturn(service);

        String serviceEntityString = objectMapper.writeValueAsString(service);
        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serviceEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(serviceEntityString));
    }

    @Test
    void testDeleteService() throws Exception {
        doNothing().when(serviceService).deleteSvc(ID);

        mockMvc.perform(delete("/service/" + ID))
                .andExpect(status().isOk());
    }
}
