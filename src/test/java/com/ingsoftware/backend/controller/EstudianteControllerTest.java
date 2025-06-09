package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Estudiante;
import com.ingsoftware.backend.services.EstudianteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
@Import(EstudianteControllerTest.MockConfig.class)
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public EstudianteService estudianteService() {
            return Mockito.mock(EstudianteService.class);
        }
    }

    @Test
    void testGetAllEstudiantes() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombres("Juan");

        Mockito.when(estudianteService.getEstudiantes()).thenReturn(Collections.singletonList(estudiante));

        mockMvc.perform(get("/api/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombres").value("Juan"));
    }

    @Test
    void testGetEstudianteById_found() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombres("Ana");

        Mockito.when(estudianteService.getEstudiante(1L)).thenReturn(Optional.of(estudiante));

        mockMvc.perform(get("/api/estudiantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Ana"));
    }

    @Test
    void testGetEstudianteById_notFound() throws Exception {
        Mockito.when(estudianteService.getEstudiante(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/estudiantes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEstudiante() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombres("Carlos");

        Mockito.when(estudianteService.createEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudiante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Carlos"));
    }

    @Test
    void testUpdateEstudiante() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombres("Modificado");

        Mockito.when(estudianteService.updateEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        mockMvc.perform(put("/api/estudiantes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudiante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Modificado"));
    }

    @Test
    void testDeleteEstudiante() throws Exception {
        Mockito.doNothing().when(estudianteService).deleteEstudiante(1L);

        mockMvc.perform(delete("/api/estudiantes/1"))
                .andExpect(status().isOk());
    }
}