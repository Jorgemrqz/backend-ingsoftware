package com.ingsoftware.backend.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

import com.ingsoftware.backend.model.EspacioFisico;
import com.ingsoftware.backend.repository.EspacioFisicoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EspacioFisicoServiceTest {


    @Mock
    private EspacioFisicoRepository espacioFisicoRepository;

    @InjectMocks
    private EspacioFisicoImpl espacioFisicoService;

    @Test
    void testGetEspaciosFisicos() {
        EspacioFisico e1 = new EspacioFisico();
        e1.setId(1L);
        e1.setNombre("Aula 1");

        when(espacioFisicoRepository.findAll()).thenReturn(List.of(e1));

        List<EspacioFisico> result = espacioFisicoService.getEspaciosFisicos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Aula 1");
    }

    @Test
    void testGetEspacioFisicoById() {
        EspacioFisico espacio = new EspacioFisico();
        espacio.setId(1L);
        espacio.setNombre("Laboratorio");

        when(espacioFisicoRepository.findById(1L)).thenReturn(Optional.of(espacio));

        Optional<EspacioFisico> result = espacioFisicoService.getEspacioFisico(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Laboratorio");
    }

    @Test
    void testCreateEspacioFisico() {
        EspacioFisico nuevo = new EspacioFisico();
        nuevo.setNombre("Aula Nueva");

        when(espacioFisicoRepository.save(nuevo)).thenReturn(nuevo);

        EspacioFisico result = espacioFisicoService.createEspacioFisico(nuevo);

        assertThat(result.getNombre()).isEqualTo("Aula Nueva");
    }

    @Test
    void testUpdateEspacioFisico() {
        EspacioFisico editado = new EspacioFisico();
        editado.setId(2L);
        editado.setNombre("Editado");

        when(espacioFisicoRepository.save(editado)).thenReturn(editado);

        EspacioFisico result = espacioFisicoService.updateEspacioFisico(editado);

        assertThat(result.getNombre()).isEqualTo("Editado");
    }

    @Test
    void testDeleteEspacioFisico() {
        doNothing().when(espacioFisicoRepository).deleteById(1L);

        espacioFisicoService.deleteEspacioFisico(1L);

        verify(espacioFisicoRepository, times(1)).deleteById(1L);
    }
}
