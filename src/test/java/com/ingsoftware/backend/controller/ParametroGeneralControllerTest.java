package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.ParametroGeneral;
import com.ingsoftware.backend.services.ParametroGeneralService;
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

@WebMvcTest(ParametroGeneralController.class)
@Import(ParametroGeneralControllerTest.MockConfig.class)
public class ParametroGeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ParametroGeneralService parametroGeneralService;

    @BeforeEach
    void setup() {
        reset(parametroGeneralService);
    }

    @Test
    void testGetAllParametrosGenerales() throws Exception {
        ParametroGeneral param = new ParametroGeneral();
        param.setId(1L);
        param.setNombre("param1");
        param.setValor("123");

        when(parametroGeneralService.getParametrosGenerales()).thenReturn(Collections.singletonList(param));

        mockMvc.perform(get("/api/parametros-generales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("param1"))
                .andExpect(jsonPath("$[0].valor").value("123"));
    }

    @Test
    void testGetParametroGeneralById_found() throws Exception {
        ParametroGeneral param = new ParametroGeneral();
        param.setId(1L);
        param.setNombre("param2");
        param.setValor("abc");

        when(parametroGeneralService.getParametroGeneral(1L)).thenReturn(Optional.of(param));

        mockMvc.perform(get("/api/parametros-generales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("param2"))
                .andExpect(jsonPath("$.valor").value("abc"));
    }

    @Test
    void testGetParametroGeneralById_notFound() throws Exception {
        when(parametroGeneralService.getParametroGeneral(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/parametros-generales/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateParametroGeneral() throws Exception {
        ParametroGeneral param = new ParametroGeneral();
        param.setNombre("nuevo");
        param.setValor("456");

        when(parametroGeneralService.createParametroGeneral(any(ParametroGeneral.class))).thenReturn(param);

        mockMvc.perform(post("/api/parametros-generales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(param)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("nuevo"))
                .andExpect(jsonPath("$.valor").value("456"));
    }

    @Test
    void testUpdateParametroGeneral() throws Exception {
        ParametroGeneral param = new ParametroGeneral();
        param.setId(1L);
        param.setNombre("editado");
        param.setValor("789");

        when(parametroGeneralService.updateParametroGeneral(any(ParametroGeneral.class))).thenReturn(param);

        mockMvc.perform(put("/api/parametros-generales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(param)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("editado"))
                .andExpect(jsonPath("$.valor").value("789"));
    }

    @Test
    void testDeleteParametroGeneral() throws Exception {
        doNothing().when(parametroGeneralService).deleteParametroGeneral(1L);

        mockMvc.perform(delete("/api/parametros-generales/1"))
                .andExpect(status().isOk());
    }

    @org.springframework.boot.test.context.TestConfiguration
    static class MockConfig {
        @org.springframework.context.annotation.Bean
        public ParametroGeneralService parametroGeneralService() {
            return mock(ParametroGeneralService.class);
        }
    }
}
