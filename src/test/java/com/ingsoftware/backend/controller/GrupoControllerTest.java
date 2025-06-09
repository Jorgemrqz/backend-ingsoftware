package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Grupo;
import com.ingsoftware.backend.services.GrupoService;
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

@WebMvcTest(GrupoController.class)
@Import(GrupoControllerTest.MockConfig.class)
public class GrupoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public GrupoService grupoService() {
            return Mockito.mock(GrupoService.class);
        }
    }

    @Test
    void testGetAllGrupos() throws Exception {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setNombre("Grupo A");

        Mockito.when(grupoService.getGrupos()).thenReturn(Collections.singletonList(grupo));

        mockMvc.perform(get("/api/grupos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Grupo A"));
    }

    @Test
    void testGetGrupoById_found() throws Exception {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setNombre("Grupo B");

        Mockito.when(grupoService.getGrupo(1L)).thenReturn(Optional.of(grupo));

        mockMvc.perform(get("/api/grupos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Grupo B"));
    }

    @Test
    void testGetGrupoById_notFound() throws Exception {
        Mockito.when(grupoService.getGrupo(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/grupos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateGrupo() throws Exception {
        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Nuevo");

        Mockito.when(grupoService.createGrupo(any(Grupo.class))).thenReturn(grupo);

        mockMvc.perform(post("/api/grupos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grupo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Grupo Nuevo"));
    }

    @Test
    void testUpdateGrupo() throws Exception {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setNombre("Grupo Editado");

        Mockito.when(grupoService.updateGrupo(any(Grupo.class))).thenReturn(grupo);

        mockMvc.perform(put("/api/grupos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grupo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Grupo Editado"));
    }

    @Test
    void testDeleteGrupo() throws Exception {
        Mockito.doNothing().when(grupoService).deleteGrupo(1L);

        mockMvc.perform(delete("/api/grupos/1"))
                .andExpect(status().isOk());
    }
}