package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Matricula;
import com.ingsoftware.backend.services.MatriculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MatriculaController.class)
@Import(MatriculaControllerTest.MockConfig.class)
public class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MatriculaService matriculaService;

    @BeforeEach
    void setup() {
        reset(matriculaService);
    }

    @Test
    void testGetAllMatriculas() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.getMatriculas()).thenReturn(Collections.singletonList(matricula));

        mockMvc.perform(get("/api/matriculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetMatriculaById_found() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.getMatricula(1L)).thenReturn(Optional.of(matricula));

        mockMvc.perform(get("/api/matriculas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetMatriculaById_notFound() throws Exception {
        when(matriculaService.getMatricula(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/matriculas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateMatricula() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.createMatricula(any(Matricula.class))).thenReturn(matricula);

        mockMvc.perform(post("/api/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matricula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateMatricula() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.updateMatricula(any(Matricula.class))).thenReturn(matricula);

        mockMvc.perform(put("/api/matriculas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matricula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteMatricula() throws Exception {
        doNothing().when(matriculaService).deleteMatricula(1L);

        mockMvc.perform(delete("/api/matriculas/1"))
                .andExpect(status().isOk());
    }

    // Mock manual para evitar @MockBean
    @org.springframework.boot.test.context.TestConfiguration
    static class MockConfig {
        @org.springframework.context.annotation.Bean
        public MatriculaService matriculaService() {
            return mock(MatriculaService.class);
        }
    }
}
