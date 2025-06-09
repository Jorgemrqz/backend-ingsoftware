package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.repository.PerfilAccesoRepository;

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
class PerfilAccesoServiceTest {

    @Mock
    private PerfilAccesoRepository perfilAccesoRepository;

    @InjectMocks
    private PerfilAccesoImpl perfilAccesoService;

    @Test
    void testGetPerfilesAcceso() {
        PerfilAcceso p1 = new PerfilAcceso();
        p1.setId(1L);
        p1.setNombre("Administrador");

        PerfilAcceso p2 = new PerfilAcceso();
        p2.setId(2L);
        p2.setNombre("Docente");

        when(perfilAccesoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PerfilAcceso> result = perfilAccesoService.getPerfilesAcceso();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Administrador");
    }

    @Test
    void testGetPerfilAcceso() {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(3L);
        perfil.setNombre("Estudiante");

        when(perfilAccesoRepository.findById(3L)).thenReturn(Optional.of(perfil));

        Optional<PerfilAcceso> result = perfilAccesoService.getPerfilAcceso(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Estudiante");
    }

    @Test
    void testCreatePerfilAccedo() {
        PerfilAcceso nuevo = new PerfilAcceso();
        nuevo.setNombre("Invitado");

        when(perfilAccesoRepository.save(nuevo)).thenReturn(nuevo);

        PerfilAcceso result = perfilAccesoService.createPerfilAccedo(nuevo);

        assertThat(result.getNombre()).isEqualTo("Invitado");
    }

    @Test
    void testUpdatePerfilAcceso() {
        PerfilAcceso actualizado = new PerfilAcceso();
        actualizado.setId(4L);
        actualizado.setNombre("Coordinador");

        when(perfilAccesoRepository.save(actualizado)).thenReturn(actualizado);

        PerfilAcceso result = perfilAccesoService.updatePerfilAcceso(actualizado);

        assertThat(result.getId()).isEqualTo(4L);
        assertThat(result.getNombre()).isEqualTo("Coordinador");
    }

    @Test
    void testDeletePerfilAcceso() {
        Long id = 5L;

        doNothing().when(perfilAccesoRepository).deleteById(id);

        perfilAccesoService.deletePerfilAcceso(id);

        verify(perfilAccesoRepository, times(1)).deleteById(id);
    }
}
