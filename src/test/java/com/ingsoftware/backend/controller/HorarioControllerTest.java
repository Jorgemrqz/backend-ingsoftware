package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Horario;
import com.ingsoftware.backend.services.HorarioService;
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

@WebMvcTest(HorarioController.class)
@Import(HorarioControllerTest.MockConfig.class)
public class HorarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HorarioService horarioService;

    @BeforeEach
    void setup() {
        reset(horarioService);
    }

    @Test
    void testGetAllHorarios() throws Exception {
        Horario horario = new Horario();
        horario.setId(1L);
        horario.setDiaSemana("Lunes");

        when(horarioService.getHorarios()).thenReturn(Collections.singletonList(horario));

        mockMvc.perform(get("/api/horarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].diaSemana").value("Lunes"));
    }

    @Test
    void testGetHorarioById_found() throws Exception {
        Horario horario = new Horario();
        horario.setId(1L);
        horario.setDiaSemana("Martes");

        when(horarioService.getHorario(1L)).thenReturn(Optional.of(horario));

        mockMvc.perform(get("/api/horarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diaSemana").value("Martes"));
    }

    @Test
    void testGetHorarioById_notFound() throws Exception {
        when(horarioService.getHorario(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/horarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateHorario() throws Exception {
        Horario horario = new Horario();
        horario.setDiaSemana("Miércoles");

        when(horarioService.createHorario(any(Horario.class))).thenReturn(horario);

        mockMvc.perform(post("/api/horarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(horario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diaSemana").value("Miércoles"));
    }

    @Test
    void testUpdateHorario() throws Exception {
        Horario horario = new Horario();
        horario.setId(1L);
        horario.setDiaSemana("Jueves");

        when(horarioService.updateHorario(any(Horario.class))).thenReturn(horario);

        mockMvc.perform(put("/api/horarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(horario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diaSemana").value("Jueves"));
    }

    @Test
    void testDeleteHorario() throws Exception {
        doNothing().when(horarioService).deleteHorario(1L);

        mockMvc.perform(delete("/api/horarios/1"))
                .andExpect(status().isOk());
    }

    // Clase interna para importar el mock manualmente
    @org.springframework.boot.test.context.TestConfiguration
    static class MockConfig {
        @org.springframework.context.annotation.Bean
        public HorarioService horarioService() {
            return mock(HorarioService.class);
        }
    }
}