package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.CuentaContable;
import com.ingsoftware.backend.model.PlanCuenta;
import com.ingsoftware.backend.services.CuentaContableService;
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

@WebMvcTest(CuentaContableController.class)
@Import(CuentaContableControllerTest.MockConfig.class)
public class CuentaContableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaContableService cuentaContableService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public CuentaContableService cuentaContableService() {
            return Mockito.mock(CuentaContableService.class);
        }
    }

    @Test
    void testGetAllCuentasContables() throws Exception {
        CuentaContable cuenta = new CuentaContable(1L, "1001", "Caja General", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.getCuentasContables())
                .thenReturn(Collections.singletonList(cuenta));

        mockMvc.perform(get("/api/cuentas-contables"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value("1001"));
    }

    @Test
    void testGetCuentaContableById_found() throws Exception {
        CuentaContable cuenta = new CuentaContable(2L, "2001", "Bancos", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.getCuentaContable(2L)).thenReturn(Optional.of(cuenta));

        mockMvc.perform(get("/api/cuentas-contables/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Bancos"));
    }

    @Test
    void testGetCuentaContableById_notFound() throws Exception {
        Mockito.when(cuentaContableService.getCuentaContable(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cuentas-contables/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCuentaContable() throws Exception {
        CuentaContable cuenta = new CuentaContable(null, "3001", "Inventario", new PlanCuenta(), null);
        CuentaContable creada = new CuentaContable(3L, "3001", "Inventario", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.createCuentaContable(any(CuentaContable.class))).thenReturn(creada);

        mockMvc.perform(post("/api/cuentas-contables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo").value("3001"));
    }

    @Test
    void testUpdateCuentaContable_found() throws Exception {
        CuentaContable cuenta = new CuentaContable(4L, "4001", "Pasivo", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.getCuentaContable(4L)).thenReturn(Optional.of(cuenta));
        Mockito.when(cuentaContableService.updateCuentaContable(any(CuentaContable.class))).thenReturn(cuenta);

        mockMvc.perform(put("/api/cuentas-contables/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pasivo"));
    }

    @Test
    void testUpdateCuentaContable_notFound() throws Exception {
        CuentaContable cuenta = new CuentaContable(999L, "9999", "No existe", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.getCuentaContable(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/cuentas-contables/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCuentaContable_found() throws Exception {
        CuentaContable cuenta = new CuentaContable(5L, "5001", "Eliminar", new PlanCuenta(), null);

        Mockito.when(cuentaContableService.getCuentaContable(5L)).thenReturn(Optional.of(cuenta));
        Mockito.doNothing().when(cuentaContableService).deleteCuentaContable(5L);

        mockMvc.perform(delete("/api/cuentas-contables/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCuentaContable_notFound() throws Exception {
        Mockito.when(cuentaContableService.getCuentaContable(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/cuentas-contables/999"))
                .andExpect(status().isNotFound());
    }
}
