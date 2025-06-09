package com.ingsoftware.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.services.PerfilAccesoService;

@WebMvcTest(PerfilAccesoController.class)
@Import(PerfilAccesoControllerTest.MockConfig.class)
public class PerfilAccesoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfilAccesoService perfilAccesoService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PerfilAccesoService perfilAccesoService() {
            return Mockito.mock(PerfilAccesoService.class); 
        }
    }

    @Test
    void testGetAllPerfiles() throws Exception {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(1L);
        perfil.setNombre("Admin");
        perfil.setDescripcion("Perfil de administrador");

        Mockito.when(perfilAccesoService.getPerfilesAcceso()).thenReturn(Collections.singletonList(perfil));

        mockMvc.perform(get("/api/perfil-acceso"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Admin"));
    }

    @Test
    void testGetPerfilById_found() throws Exception {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(1L);
        perfil.setNombre("User");

        Mockito.when(perfilAccesoService.getPerfilAcceso(1L)).thenReturn(Optional.of(perfil));

        mockMvc.perform(get("/api/perfil-acceso/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("User"));
    }

    @Test
    void testGetPerfilById_notFound() throws Exception {
        Mockito.when(perfilAccesoService.getPerfilAcceso(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/perfil-acceso/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePerfil() throws Exception {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setNombre("Nuevo");
        perfil.setDescripcion("Descripción de prueba");

        Mockito.when(perfilAccesoService.createPerfilAccedo(any(PerfilAcceso.class))).thenReturn(perfil);

        mockMvc.perform(post("/api/perfil-acceso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(perfil)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo"));
    }

    @Test
    void testUpdatePerfil() throws Exception {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(1L);
        perfil.setNombre("Actualizado");

        Mockito.when(perfilAccesoService.updatePerfilAcceso(any(PerfilAcceso.class))).thenReturn(perfil);

        mockMvc.perform(put("/api/perfil-acceso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(perfil)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Actualizado"));
    }

    @Test
    void testDeletePerfil_found() throws Exception {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(1L);
        perfil.setNombre("Eliminar");

        Mockito.when(perfilAccesoService.getPerfilAcceso(1L)).thenReturn(Optional.of(perfil));
        Mockito.doNothing().when(perfilAccesoService).deletePerfilAcceso(1L);

        mockMvc.perform(delete("/api/perfil-acceso/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePerfil_notFound() throws Exception {
        Mockito.when(perfilAccesoService.getPerfilAcceso(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/perfil-acceso/999"))
                .andExpect(status().isBadRequest());
    }
}
