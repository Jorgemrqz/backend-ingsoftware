package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Calificacion;
import com.ingsoftware.backend.services.CalificacionService;
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

@WebMvcTest(CalificacionController.class)
public class CalificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionService calificacionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCalificaciones() throws Exception {
        Calificacion c = new Calificacion();
        c.setId(1L);
        c.setNota(95.0);

        when(calificacionService.getCalificaciones()).thenReturn(Collections.singletonList(c));

        mockMvc.perform(get("/api/calificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nota").value(95.0));
    }

    @Test
    void testGetCalificacionById_found() throws Exception {
        Calificacion c = new Calificacion();
        c.setId(1L);
        c.setNota(88.5);

        when(calificacionService.getCalificacion(1L)).thenReturn(Optional.of(c));

        mockMvc.perform(get("/api/calificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nota").value(88.5));
    }

    @Test
    void testGetCalificacionById_notFound() throws Exception {
        when(calificacionService.getCalificacion(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/calificaciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCalificacion() throws Exception {
        Calificacion nueva = new Calificacion();
        nueva.setNota(100.0);

        when(calificacionService.createCalificacion(any(Calificacion.class))).thenReturn(nueva);

        mockMvc.perform(post("/api/calificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nota").value(100.0));
    }

    @Test
    void testUpdateCalificacion_found() throws Exception {
        Calificacion calificacion = new Calificacion();
        calificacion.setId(1L);
        calificacion.setNota(75.0);

        when(calificacionService.getCalificacion(1L)).thenReturn(Optional.of(calificacion));
        when(calificacionService.updateCalificacion(any(Calificacion.class))).thenReturn(calificacion);

        mockMvc.perform(put("/api/calificaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calificacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nota").value(75.0));
    }

    @Test
    void testUpdateCalificacion_notFound() throws Exception {
        when(calificacionService.getCalificacion(99L)).thenReturn(Optional.empty());

        Calificacion calificacion = new Calificacion();
        calificacion.setNota(60.0);

        mockMvc.perform(put("/api/calificaciones/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calificacion)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCalificacion_found() throws Exception {
        Calificacion calificacion = new Calificacion();
        calificacion.setId(1L);

        when(calificacionService.getCalificacion(1L)).thenReturn(Optional.of(calificacion));
        Mockito.doNothing().when(calificacionService).deleteCalificacion(1L);

        mockMvc.perform(delete("/api/calificaciones/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCalificacion_notFound() throws Exception {
        when(calificacionService.getCalificacion(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/calificaciones/99"))
                .andExpect(status().isNotFound());
    }
}
