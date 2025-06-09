package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Asignatura;
import com.ingsoftware.backend.services.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AsignaturaController.class)
public class AsignaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsignaturaService asignaturaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAsignaturas() throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Matemáticas");

        when(asignaturaService.getAsignaturas()).thenReturn(Collections.singletonList(asignatura));

        mockMvc.perform(get("/api/asignaturas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Matemáticas"));
    }

    @Test
    void testGetAsignaturaById_found() throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Física");

        when(asignaturaService.getAsignatura(1L)).thenReturn(Optional.of(asignatura));

        mockMvc.perform(get("/api/asignaturas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Física"));
    }

    @Test
    void testGetAsignaturaById_notFound() throws Exception {
        when(asignaturaService.getAsignatura(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/asignaturas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAsignatura() throws Exception {
        Asignatura nueva = new Asignatura();
        nueva.setNombre("Biología");

        when(asignaturaService.createAsignatura(any(Asignatura.class))).thenReturn(nueva);

        mockMvc.perform(post("/api/asignaturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Biología"));
    }

    @Test
    void testUpdateAsignatura_found() throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);
        asignatura.setNombre("Editada");

        when(asignaturaService.getAsignatura(1L)).thenReturn(Optional.of(asignatura));
        when(asignaturaService.updateAsignatura(any(Asignatura.class))).thenReturn(asignatura);

        mockMvc.perform(put("/api/asignaturas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(asignatura)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Editada"));
    }

    @Test
    void testUpdateAsignatura_notFound() throws Exception {
        when(asignaturaService.getAsignatura(99L)).thenReturn(Optional.empty());

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Nueva");

        mockMvc.perform(put("/api/asignaturas/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(asignatura)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAsignatura_found() throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1L);

        when(asignaturaService.getAsignatura(1L)).thenReturn(Optional.of(asignatura));
        Mockito.doNothing().when(asignaturaService).deleteAsignatura(1L);

        mockMvc.perform(delete("/api/asignaturas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAsignatura_notFound() throws Exception {
        when(asignaturaService.getAsignatura(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/asignaturas/99"))
                .andExpect(status().isNotFound());
    }
}
