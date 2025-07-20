package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Docente;
import com.ingsoftware.backend.services.DocenteService;
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

@WebMvcTest(DocenteController.class)
@Import(DocenteControllerTest.MockConfig.class)
public class DocenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public DocenteService docenteService() {
            return Mockito.mock(DocenteService.class);
        }
    }

    @Test
    void testGetAllDocentes() throws Exception {
        Docente d = new Docente();
        d.setId(1L);
        d.setNombres("Juan");
        d.setApellidos("Pérez");

        Mockito.when(docenteService.getDocentes()).thenReturn(Collections.singletonList(d));

        mockMvc.perform(get("/api/docentes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombres").value("Juan"));
    }

    @Test
    void testGetDocenteById_found() throws Exception {
        Docente d = new Docente();
        d.setId(2L);
        d.setNombres("María");

        Mockito.when(docenteService.getDocente(2L)).thenReturn(Optional.of(d));

        mockMvc.perform(get("/api/docentes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("María"));
    }

    @Test
    void testGetDocenteById_notFound() throws Exception {
        Mockito.when(docenteService.getDocente(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/docentes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateDocente() throws Exception {
        Docente d = new Docente();
        d.setNombres("Ana");
        d.setApellidos("Lopez");
        d.setEspecialidad("Matemáticas");

        mockMvc.perform(post("/api/docentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombres").value("Ana"));
    }

    @Test
    void testUpdateDocente_found() throws Exception {
        Docente d = new Docente();
        d.setId(3L);
        d.setNombres("Luis");

        Mockito.when(docenteService.getDocente(3L)).thenReturn(Optional.of(d));
        Mockito.when(docenteService.updateDocente(any(Docente.class))).thenReturn(d);

        mockMvc.perform(put("/api/docentes/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Luis"));
    }

    @Test
    void testUpdateDocente_notFound() throws Exception {
        Docente d = new Docente();
        d.setId(999L);
        d.setNombres("No Existe");

        Mockito.when(docenteService.getDocente(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/docentes/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDocente_found() throws Exception {
        Docente d = new Docente();
        d.setId(4L);

        Mockito.when(docenteService.getDocente(4L)).thenReturn(Optional.of(d));
        Mockito.doNothing().when(docenteService).deleteDocente(4L);

        mockMvc.perform(delete("/api/docentes/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteDocente_notFound() throws Exception {
        Mockito.when(docenteService.getDocente(888L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/docentes/888"))
                .andExpect(status().isNotFound());
    }
}
