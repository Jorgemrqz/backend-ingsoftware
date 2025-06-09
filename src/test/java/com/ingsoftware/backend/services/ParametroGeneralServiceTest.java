package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.ParametroGeneral;
import com.ingsoftware.backend.repository.ParametroGeneralRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParametroGeneralServiceTest {

    @Mock
    private ParametroGeneralRepository parametroGeneralRepository;

    @InjectMocks
    private ParametroGeneralImpl parametroGeneralService;

    @Test
    void testGetParametrosGenerales() {
        ParametroGeneral p1 = new ParametroGeneral();
        p1.setId(1L);
        p1.setNombre("Año Lectivo");

        when(parametroGeneralRepository.findAll()).thenReturn(List.of(p1));

        List<ParametroGeneral> result = parametroGeneralService.getParametrosGenerales();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Año Lectivo");
    }

    @Test
    void testGetParametroGeneralById() {
        ParametroGeneral parametro = new ParametroGeneral();
        parametro.setId(1L);
        parametro.setNombre("Inicio de Clases");

        when(parametroGeneralRepository.findById(1L)).thenReturn(Optional.of(parametro));

        Optional<ParametroGeneral> result = parametroGeneralService.getParametroGeneral(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Inicio de Clases");
    }

    @Test
    void testCreateParametroGeneral() {
        ParametroGeneral nuevo = new ParametroGeneral();
        nuevo.setNombre("Duración Periodo");

        when(parametroGeneralRepository.save(nuevo)).thenReturn(nuevo);

        ParametroGeneral result = parametroGeneralService.createParametroGeneral(nuevo);

        assertThat(result.getNombre()).isEqualTo("Duración Periodo");
    }

    @Test
    void testUpdateParametroGeneral() {
        ParametroGeneral modificado = new ParametroGeneral();
        modificado.setId(2L);
        modificado.setNombre("Modificado");

        when(parametroGeneralRepository.save(modificado)).thenReturn(modificado);

        ParametroGeneral result = parametroGeneralService.updateParametroGeneral(modificado);

        assertThat(result.getNombre()).isEqualTo("Modificado");
    }

    @Test
    void testDeleteParametroGeneral() {
        doNothing().when(parametroGeneralRepository).deleteById(1L);

        parametroGeneralService.deleteParametroGeneral(1L);

        verify(parametroGeneralRepository, times(1)).deleteById(1L);
    }

}
