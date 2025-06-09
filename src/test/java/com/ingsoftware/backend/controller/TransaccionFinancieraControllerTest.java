package com.ingsoftware.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
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
import com.ingsoftware.backend.model.TransaccionFinanciera;
import com.ingsoftware.backend.services.TransaccionFinancieraService;

@WebMvcTest(TransaccionFinancieraController.class)
@Import(TransaccionFinancieraControllerTest.MockConfig.class)
public class TransaccionFinancieraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransaccionFinancieraService transaccionService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public TransaccionFinancieraService transaccionService() {
            return Mockito.mock(TransaccionFinancieraService.class);
        }
    }

    @Test
    void testGetAllTransacciones() throws Exception {
        TransaccionFinanciera t = new TransaccionFinanciera();
        t.setId(1L);
        t.setTipo("Ingreso");
        t.setDescripcion("Pago matrícula");
        t.setMonto(new BigDecimal("150.00"));
        t.setFecha(LocalDate.of(2024, 1, 1));

        Mockito.when(transaccionService.getTransaccionesFinancieras()).thenReturn(Collections.singletonList(t));

        mockMvc.perform(get("/api/transaccion-financiera"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("Ingreso"))
                .andExpect(jsonPath("$[0].descripcion").value("Pago matrícula"));
    }

    @Test
    void testGetTransaccionById_found() throws Exception {
        TransaccionFinanciera t = new TransaccionFinanciera();
        t.setId(1L);
        t.setTipo("Egreso");

        Mockito.when(transaccionService.getTransaccionFinanciera(1L)).thenReturn(Optional.of(t));

        mockMvc.perform(get("/api/transaccion-financiera/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Egreso"));
    }

    @Test
    void testGetTransaccionById_notFound() throws Exception {
        Mockito.when(transaccionService.getTransaccionFinanciera(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/transaccion-financiera/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTransaccion() throws Exception {
        TransaccionFinanciera t = new TransaccionFinanciera();
        t.setTipo("Ingreso");
        t.setDescripcion("Pago en efectivo");
        t.setMonto(new BigDecimal("200.00"));
        t.setFecha(LocalDate.now());

        Mockito.when(transaccionService.createTransaccionFinanciera(any(TransaccionFinanciera.class))).thenReturn(t);

        mockMvc.perform(post("/api/transaccion-financiera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Pago en efectivo"));
    }

    @Test
    void testUpdateTransaccion() throws Exception {
        TransaccionFinanciera t = new TransaccionFinanciera();
        t.setId(1L);
        t.setTipo("Egreso");
        t.setDescripcion("Pago de servicios");

        Mockito.when(transaccionService.updateTransaccionFinanciera(any(TransaccionFinanciera.class))).thenReturn(t);

        mockMvc.perform(put("/api/transaccion-financiera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Pago de servicios"));
    }

    @Test
    void testDeleteTransaccion_found() throws Exception {
        TransaccionFinanciera t = new TransaccionFinanciera();
        t.setId(1L);

        Mockito.when(transaccionService.getTransaccionFinanciera(1L)).thenReturn(Optional.of(t));
        Mockito.doNothing().when(transaccionService).deleteTransaccionFinanciera(1L);

        mockMvc.perform(delete("/api/transaccion-financiera/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTransaccion_notFound() throws Exception {
        Mockito.when(transaccionService.getTransaccionFinanciera(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/transaccion-financiera/999"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateTransaccionFinanciera_exception_returnsBadRequest() throws Exception {
        TransaccionFinanciera transaccion = new TransaccionFinanciera();
        transaccion.setDescripcion("Pago matrícula");

        Mockito.when(transaccionService.createTransaccionFinanciera(any(TransaccionFinanciera.class)))
            .thenThrow(new RuntimeException("Error simulado"));

        mockMvc.perform(post("/api/transaccion-financiera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaccion)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateTransaccionFinanciera_exception_returnsBadRequest() throws Exception {
        TransaccionFinanciera transaccion = new TransaccionFinanciera();
        transaccion.setId(1L);
        transaccion.setDescripcion("Actualización");

        Mockito.when(transaccionService.updateTransaccionFinanciera(any(TransaccionFinanciera.class)))
            .thenThrow(new RuntimeException("Error simulado"));

        mockMvc.perform(put("/api/transaccion-financiera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaccion)))
            .andExpect(status().isBadRequest());
    }

}
