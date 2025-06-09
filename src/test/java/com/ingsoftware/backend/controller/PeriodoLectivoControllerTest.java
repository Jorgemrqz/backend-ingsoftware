package com.ingsoftware.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.PeriodoLectivo;
import com.ingsoftware.backend.services.PeriodoLectivoService;

@WebMvcTest(PeriodoLectivoController.class)
@Import(PeriodoLectivoControllerTest.MockConfig.class)
public class PeriodoLectivoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeriodoLectivoService periodoLectivoService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PeriodoLectivoService periodoLectivoService() {
            return Mockito.mock(PeriodoLectivoService.class);
        }
    }

    @Test
    void testGetAllPeriodos() throws Exception {
        PeriodoLectivo periodo = new PeriodoLectivo();
        periodo.setId(1L);
        periodo.setNombre("2024-A");
        periodo.setFechaInicio(LocalDate.of(2024, 1, 1));
        periodo.setFechaFin(LocalDate.of(2024, 6, 30));

        Mockito.when(periodoLectivoService.getPeriodosLectivos()).thenReturn(Collections.singletonList(periodo));

        mockMvc.perform(get("/api/periodo-lectivo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("2024-A"));
    }

    @Test
    void testGetPeriodoById_found() throws Exception {
        PeriodoLectivo periodo = new PeriodoLectivo();
        periodo.setId(1L);
        periodo.setNombre("2024-B");

        Mockito.when(periodoLectivoService.getPeriodoLectivo(1L)).thenReturn(Optional.of(periodo));

        mockMvc.perform(get("/api/periodo-lectivo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("2024-B"));
    }

    @Test
    void testGetPeriodoById_notFound() throws Exception {
        Mockito.when(periodoLectivoService.getPeriodoLectivo(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/periodo-lectivo/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePeriodo() throws Exception {
        PeriodoLectivo periodo = new PeriodoLectivo();
        periodo.setNombre("2025-A");
        periodo.setFechaInicio(LocalDate.of(2025, 1, 1));
        periodo.setFechaFin(LocalDate.of(2025, 6, 30));

        Mockito.when(periodoLectivoService.createPeriodoLectivo(any(PeriodoLectivo.class))).thenReturn(periodo);

        mockMvc.perform(post("/api/periodo-lectivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(periodo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("2025-A"));
    }

    @Test
    void testUpdatePeriodo() throws Exception {
        PeriodoLectivo periodo = new PeriodoLectivo();
        periodo.setId(1L);
        periodo.setNombre("2025-B");

        Mockito.when(periodoLectivoService.updatePeriodoLectivo(any(PeriodoLectivo.class))).thenReturn(periodo);

        mockMvc.perform(put("/api/periodo-lectivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(periodo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("2025-B"));
    }

    @Test
    void testDeletePeriodo_found() throws Exception {
        PeriodoLectivo periodo = new PeriodoLectivo();
        periodo.setId(1L);
        periodo.setNombre("2023-A");

        Mockito.when(periodoLectivoService.getPeriodoLectivo(1L)).thenReturn(Optional.of(periodo));
        Mockito.doNothing().when(periodoLectivoService).deletePeriodoLectivo(1L);

        mockMvc.perform(delete("/api/periodo-lectivo/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePeriodo_notFound() throws Exception {
        Mockito.when(periodoLectivoService.getPeriodoLectivo(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/periodo-lectivo/999"))
                .andExpect(status().isBadRequest());
    }

    @Test
void testCreatePeriodoLectivo_exceptionThrown_returnsBadRequest() throws Exception {
    PeriodoLectivo periodo = new PeriodoLectivo();
    periodo.setNombre("Error");
    periodo.setFechaInicio(LocalDate.of(2024, 1, 1));
    periodo.setFechaFin(LocalDate.of(2024, 6, 1));

    Mockito.when(periodoLectivoService.createPeriodoLectivo(any(PeriodoLectivo.class)))
           .thenThrow(new RuntimeException("Error simulado"));

    mockMvc.perform(post("/api/periodo-lectivo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(periodo)))
           .andExpect(status().isBadRequest());
}

@Test
void testUpdatePeriodoLectivo_exceptionThrown_returnsBadRequest() throws Exception {
    PeriodoLectivo periodo = new PeriodoLectivo();
    periodo.setId(1L);
    periodo.setNombre("Actualizar Fallido");
    periodo.setFechaInicio(LocalDate.of(2024, 1, 1));
    periodo.setFechaFin(LocalDate.of(2024, 6, 1));

    Mockito.when(periodoLectivoService.updatePeriodoLectivo(any(PeriodoLectivo.class)))
           .thenThrow(new RuntimeException("Error simulado"));

    mockMvc.perform(put("/api/periodo-lectivo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(periodo)))
           .andExpect(status().isBadRequest());
}

}
