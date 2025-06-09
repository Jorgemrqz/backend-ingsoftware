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
import com.ingsoftware.backend.model.PlanCuenta;
import com.ingsoftware.backend.services.PlanCuentaService;

@WebMvcTest(PlanCuentaController.class)
@Import(PlanCuentaControllerTest.MockConfig.class)
public class PlanCuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanCuentaService planCuentaService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PlanCuentaService planCuentaService() {
            return Mockito.mock(PlanCuentaService.class);
        }
    }

    @Test
    void testGetAllPlanes() throws Exception {
        PlanCuenta plan = new PlanCuenta();
        plan.setId(1L);
        plan.setNombre("Plan General");
        plan.setDescripcion("Descripción básica");

        Mockito.when(planCuentaService.getPlanes()).thenReturn(Collections.singletonList(plan));

        mockMvc.perform(get("/api/plan-cuenta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Plan General"));
    }

    @Test
    void testGetPlanById_found() throws Exception {
        PlanCuenta plan = new PlanCuenta();
        plan.setId(1L);
        plan.setNombre("Plan Detallado");

        Mockito.when(planCuentaService.getPlan(1L)).thenReturn(Optional.of(plan));

        mockMvc.perform(get("/api/plan-cuenta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Plan Detallado"));
    }

    @Test
    void testGetPlanById_notFound() throws Exception {
        Mockito.when(planCuentaService.getPlan(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/plan-cuenta/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePlan() throws Exception {
        PlanCuenta plan = new PlanCuenta();
        plan.setNombre("Nuevo Plan");
        plan.setDescripcion("Plan creado para pruebas");

        Mockito.when(planCuentaService.createPlanCuenta(any(PlanCuenta.class))).thenReturn(plan);

        mockMvc.perform(post("/api/plan-cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Plan"));
    }

    @Test
    void testUpdatePlan() throws Exception {
        PlanCuenta plan = new PlanCuenta();
        plan.setId(1L);
        plan.setNombre("Plan Actualizado");

        Mockito.when(planCuentaService.updatePlanCuenta(any(PlanCuenta.class))).thenReturn(plan);

        mockMvc.perform(put("/api/plan-cuenta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Plan Actualizado"));
    }

    @Test
    void testDeletePlan_found() throws Exception {
        PlanCuenta plan = new PlanCuenta();
        plan.setId(1L);

        Mockito.when(planCuentaService.getPlan(1L)).thenReturn(Optional.of(plan));
        Mockito.doNothing().when(planCuentaService).deletePlanCuenta(1L);

        mockMvc.perform(delete("/api/plan-cuenta/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePlan_notFound() throws Exception {
        Mockito.when(planCuentaService.getPlan(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/plan-cuenta/999"))
                .andExpect(status().isBadRequest());
    }
}
