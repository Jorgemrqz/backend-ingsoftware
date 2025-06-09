package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.DiarioCaja;
import com.ingsoftware.backend.services.DiarioCajaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiarioCajaController.class)
@Import(DiarioCajaControllerTest.MockConfig.class)
public class DiarioCajaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DiarioCajaService diarioCajaService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public DiarioCajaService diarioCajaService() {
            return Mockito.mock(DiarioCajaService.class);
        }
    }

    @Test
    void testGetAllDiarios() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setId(1L);
        d.setFecha(LocalDate.of(2024, 1, 10));

        Mockito.when(diarioCajaService.getDiariosCaja()).thenReturn(Collections.singletonList(d));

        mockMvc.perform(get("/api/diarios-caja"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fecha").value("2024-01-10"));
    }

    @Test
    void testGetDiarioById_found() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setId(1L);
        d.setFecha(LocalDate.of(2024, 3, 15));

        Mockito.when(diarioCajaService.getDiarioCaja(1L)).thenReturn(Optional.of(d));

        mockMvc.perform(get("/api/diarios-caja/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha").value("2024-03-15"));
    }

    @Test
    void testGetDiarioById_notFound() throws Exception {
        Mockito.when(diarioCajaService.getDiarioCaja(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/diarios-caja/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateDiario() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setFecha(LocalDate.of(2024, 5, 5));

        DiarioCaja creado = new DiarioCaja(1L, d.getFecha(), null);

        Mockito.when(diarioCajaService.createDiarioCaja(any(DiarioCaja.class))).thenReturn(creado);

        mockMvc.perform(post("/api/diarios-caja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fecha").value("2024-05-05"));
    }

    @Test
    void testUpdateDiario_found() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setId(3L);
        d.setFecha(LocalDate.of(2024, 8, 15));

        Mockito.when(diarioCajaService.getDiarioCaja(3L)).thenReturn(Optional.of(d));
        Mockito.when(diarioCajaService.updateDiarioCaja(any(DiarioCaja.class))).thenReturn(d);

        mockMvc.perform(put("/api/diarios-caja/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha").value("2024-08-15"));
    }

    @Test
    void testUpdateDiario_notFound() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setId(999L);
        d.setFecha(LocalDate.of(2024, 12, 1));

        Mockito.when(diarioCajaService.getDiarioCaja(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/diarios-caja/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDiario_found() throws Exception {
        DiarioCaja d = new DiarioCaja();
        d.setId(4L);

        Mockito.when(diarioCajaService.getDiarioCaja(4L)).thenReturn(Optional.of(d));
        Mockito.doNothing().when(diarioCajaService).deleteDiarioCaja(4L);

        mockMvc.perform(delete("/api/diarios-caja/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteDiario_notFound() throws Exception {
        Mockito.when(diarioCajaService.getDiarioCaja(777L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/diarios-caja/777"))
                .andExpect(status().isNotFound());
    }
}
