package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.ComprobanteVenta;
import com.ingsoftware.backend.services.ComprobanteVentaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComprobanteVentaController.class)
@Import(ComprobanteVentaControllerTest.MockConfig.class)
public class ComprobanteVentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComprobanteVentaService comprobanteVentaService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ComprobanteVentaService comprobanteVentaService() {
            return Mockito.mock(ComprobanteVentaService.class);
        }
    }

    @Test
    void testGetAllComprobantes() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(1L);
        c.setNumero("001-001-000000123");
        c.setFechaEmision(LocalDate.of(2024, 1, 1));
        c.setTotal(new BigDecimal("50.00"));

        Mockito.when(comprobanteVentaService.getComprobantesVenta()).thenReturn(Collections.singletonList(c));

        mockMvc.perform(get("/api/comprobantes-venta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numero").value("001-001-000000123"));
    }

    @Test
    void testGetComprobanteById_found() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(2L);
        c.setNumero("001-001-000000456");

        Mockito.when(comprobanteVentaService.getComprobanteVenta(2L)).thenReturn(Optional.of(c));

        mockMvc.perform(get("/api/comprobantes-venta/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("001-001-000000456"));
    }

    @Test
    void testGetComprobanteById_notFound() throws Exception {
        Mockito.when(comprobanteVentaService.getComprobanteVenta(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/comprobantes-venta/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateComprobante() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setNumero("001-001-000000789");
        c.setFechaEmision(LocalDate.of(2024, 2, 2));
        c.setTotal(new BigDecimal("75.00"));

        ComprobanteVenta creado = new ComprobanteVenta(1L, c.getNumero(), c.getFechaEmision(), c.getTotal(), null, null);

        Mockito.when(comprobanteVentaService.createComprobanteVenta(any(ComprobanteVenta.class))).thenReturn(creado);

        mockMvc.perform(post("/api/comprobantes-venta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numero").value("001-001-000000789"));
    }

    @Test
    void testUpdateComprobante_found() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(3L);
        c.setNumero("001-001-000000999");

        Mockito.when(comprobanteVentaService.getComprobanteVenta(3L)).thenReturn(Optional.of(c));
        Mockito.when(comprobanteVentaService.updateComprobanteVenta(any(ComprobanteVenta.class))).thenReturn(c);

        mockMvc.perform(put("/api/comprobantes-venta/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("001-001-000000999"));
    }

    @Test
    void testUpdateComprobante_notFound() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(999L);
        c.setNumero("001-001-000009999");

        Mockito.when(comprobanteVentaService.getComprobanteVenta(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/comprobantes-venta/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteComprobante_found() throws Exception {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(4L);

        Mockito.when(comprobanteVentaService.getComprobanteVenta(4L)).thenReturn(Optional.of(c));
        Mockito.doNothing().when(comprobanteVentaService).deleteComprobanteVenta(4L);

        mockMvc.perform(delete("/api/comprobantes-venta/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteComprobante_notFound() throws Exception {
        Mockito.when(comprobanteVentaService.getComprobanteVenta(888L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/comprobantes-venta/888"))
                .andExpect(status().isNotFound());
    }
}
