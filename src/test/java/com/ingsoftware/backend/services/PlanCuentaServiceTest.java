package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.PlanCuenta;
import com.ingsoftware.backend.repository.PlanCuentaRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanCuentaServiceTest {

    @Mock
    private PlanCuentaRepository planCuentaRepository;

    @InjectMocks
    private PlanCuentaImpl planCuentaService;

    @Test
    void testGetPlanes() {
        PlanCuenta p1 = new PlanCuenta();
        p1.setId(1L);
        p1.setNombre("Plan A");

        PlanCuenta p2 = new PlanCuenta();
        p2.setId(2L);
        p2.setNombre("Plan B");

        when(planCuentaRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PlanCuenta> result = planCuentaService.getPlanes();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Plan A");
    }

    @Test
    void testGetPlan() {
        PlanCuenta plan = new PlanCuenta();
        plan.setId(1L);
        plan.setNombre("Plan Único");

        when(planCuentaRepository.findById(1L)).thenReturn(Optional.of(plan));

        Optional<PlanCuenta> result = planCuentaService.getPlan(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Plan Único");
    }

    @Test
    void testCreatePlanCuenta() {
        PlanCuenta nuevo = new PlanCuenta();
        nuevo.setNombre("Nuevo Plan");

        when(planCuentaRepository.save(nuevo)).thenReturn(nuevo);

        PlanCuenta result = planCuentaService.createPlanCuenta(nuevo);

        assertThat(result.getNombre()).isEqualTo("Nuevo Plan");
    }

    @Test
    void testUpdatePlanCuenta() {
        PlanCuenta actualizado = new PlanCuenta();
        actualizado.setId(3L);
        actualizado.setNombre("Plan Actualizado");

        when(planCuentaRepository.save(actualizado)).thenReturn(actualizado);

        PlanCuenta result = planCuentaService.updatePlanCuenta(actualizado);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getNombre()).isEqualTo("Plan Actualizado");
    }

    @Test
    void testDeletePlanCuenta() {
        Long id = 10L;

        doNothing().when(planCuentaRepository).deleteById(id);

        planCuentaService.deletePlanCuenta(id);

        verify(planCuentaRepository, times(1)).deleteById(id);
    }
}
