package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.Rubro;
import com.ingsoftware.backend.repository.RubroRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RubroServiceTest {

    @Mock
    private RubroRepository rubroRepository;

    @InjectMocks
    private RubroImpl rubroService;

    @Test
    void testGetRubros() {
        Rubro r1 = new Rubro();
        r1.setId(1L);
        r1.setDescripcion("Inscripción");

        Rubro r2 = new Rubro();
        r2.setId(2L);
        r2.setDescripcion("Mensualidad");

        when(rubroRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Rubro> result = rubroService.getRubros();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDescripcion()).isEqualTo("Inscripción");
    }

    @Test
    void testGetRubro() {
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubro.setDescripcion("Matrícula");

        when(rubroRepository.findById(1L)).thenReturn(Optional.of(rubro));

        Optional<Rubro> result = rubroService.getRubro(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getDescripcion()).isEqualTo("Matrícula");
    }

    @Test
    void testCreateRubro() {
        Rubro nuevo = new Rubro();
        nuevo.setDescripcion("Laboratorio");
        nuevo.setMonto(BigDecimal.valueOf(150));

        when(rubroRepository.save(nuevo)).thenReturn(nuevo);

        Rubro result = rubroService.createRubro(nuevo);

        assertThat(result.getDescripcion()).isEqualTo("Laboratorio");
        assertThat(result.getMonto()).isEqualByComparingTo(BigDecimal.valueOf(150));
    }

    @Test
    void testUpdateRubro() {
        Rubro actualizado = new Rubro();
        actualizado.setId(3L);
        actualizado.setDescripcion("Reinscripción");

        when(rubroRepository.save(actualizado)).thenReturn(actualizado);

        Rubro result = rubroService.updateRubro(actualizado);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getDescripcion()).isEqualTo("Reinscripción");
    }

    @Test
    void testDeleteRubro() {
        Long id = 5L;

        doNothing().when(rubroRepository).deleteById(id);

        rubroService.deleteRubro(id);

        verify(rubroRepository, times(1)).deleteById(id);
    }
}
