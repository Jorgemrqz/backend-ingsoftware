package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.EspacioFisico;
import com.ingsoftware.backend.services.EspacioFisicoService;
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

@WebMvcTest(EspacioFisicoController.class)
@Import(EspacioFisicoControllerTest.Config.class)
public class EspacioFisicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EspacioFisicoService espacioFisicoService;

    @TestConfiguration
    static class Config {
        @Bean
        public EspacioFisicoService espacioFisicoService() {
            return Mockito.mock(EspacioFisicoService.class);
        }
    }

    @Test
    void testGetAllEspacios() throws Exception {
        EspacioFisico espacio = new EspacioFisico();
        espacio.setId(1L);
        espacio.setNombre("Aula A");

        Mockito.when(espacioFisicoService.getEspaciosFisicos()).thenReturn(Collections.singletonList(espacio));

        mockMvc.perform(get("/api/espacios-fisicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Aula A"));
    }

    @Test
    void testGetEspacioById_found() throws Exception {
        EspacioFisico espacio = new EspacioFisico();
        espacio.setId(1L);
        espacio.setNombre("Laboratorio");

        Mockito.when(espacioFisicoService.getEspacioFisico(1L)).thenReturn(Optional.of(espacio));

        mockMvc.perform(get("/api/espacios-fisicos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laboratorio"));
    }

    @Test
    void testGetEspacioById_notFound() throws Exception {
        Mockito.when(espacioFisicoService.getEspacioFisico(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/espacios-fisicos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEspacio() throws Exception {
        EspacioFisico espacio = new EspacioFisico();
        espacio.setNombre("Nuevo Espacio");

        Mockito.when(espacioFisicoService.createEspacioFisico(any(EspacioFisico.class))).thenReturn(espacio);

        mockMvc.perform(post("/api/espacios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(espacio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Espacio"));
    }

    @Test
    void testUpdateEspacio() throws Exception {
        EspacioFisico espacio = new EspacioFisico();
        espacio.setId(1L);
        espacio.setNombre("Editado");

        Mockito.when(espacioFisicoService.updateEspacioFisico(any(EspacioFisico.class))).thenReturn(espacio);

        mockMvc.perform(put("/api/espacios-fisicos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(espacio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Editado"));
    }

    @Test
    void testDeleteEspacio() throws Exception {
        Mockito.doNothing().when(espacioFisicoService).deleteEspacioFisico(1L);

        mockMvc.perform(delete("/api/espacios-fisicos/1"))
                .andExpect(status().isOk());
    }
}