package com.ingsoftware.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
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
import com.ingsoftware.backend.model.Rubro;
import com.ingsoftware.backend.services.RubroService;

@WebMvcTest(RubroController.class)
@Import(RubroControllerTest.MockConfig.class)
public class RubroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RubroService rubroService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public RubroService rubroService() {
            return Mockito.mock(RubroService.class);
        }
    }

    @Test
    void testGetAllRubros() throws Exception {
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubro.setDescripcion("Matrícula");
        rubro.setMonto(new BigDecimal("150.00"));

        Mockito.when(rubroService.getRubros()).thenReturn(Collections.singletonList(rubro));

        mockMvc.perform(get("/api/rubro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descripcion").value("Matrícula"));
    }

    @Test
    void testGetRubroById_found() throws Exception {
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubro.setDescripcion("Pensión");

        Mockito.when(rubroService.getRubro(1L)).thenReturn(Optional.of(rubro));

        mockMvc.perform(get("/api/rubro/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Pensión"));
    }

    @Test
    void testGetRubroById_notFound() throws Exception {
        Mockito.when(rubroService.getRubro(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/rubro/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateRubro() throws Exception {
        Rubro rubro = new Rubro();
        rubro.setDescripcion("Inscripción");
        rubro.setMonto(new BigDecimal("80.00"));

        Mockito.when(rubroService.createRubro(any(Rubro.class))).thenReturn(rubro);

        mockMvc.perform(post("/api/rubro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rubro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Inscripción"));
    }

    @Test
    void testUpdateRubro() throws Exception {
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubro.setDescripcion("Rubro Modificado");

        Mockito.when(rubroService.updateRubro(any(Rubro.class))).thenReturn(rubro);

        mockMvc.perform(put("/api/rubro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rubro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Rubro Modificado"));
    }

    @Test
    void testDeleteRubro_found() throws Exception {
        Rubro rubro = new Rubro();
        rubro.setId(1L);

        Mockito.when(rubroService.getRubro(1L)).thenReturn(Optional.of(rubro));
        Mockito.doNothing().when(rubroService).deleteRubro(1L);

        mockMvc.perform(delete("/api/rubro/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteRubro_notFound() throws Exception {
        Mockito.when(rubroService.getRubro(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/rubro/999"))
                .andExpect(status().isBadRequest());
    }
}

