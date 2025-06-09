package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.Grupo;
import com.ingsoftware.backend.repository.GrupoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {

    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private GrupoImpl grupoService;

    @Test
    void testGetGrupos() {
        Grupo g1 = new Grupo();
        g1.setId(1L);
        g1.setNombre("Grupo A");

        when(grupoRepository.findAll()).thenReturn(List.of(g1));

        List<Grupo> result = grupoService.getGrupos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Grupo A");
    }

    @Test
    void testGetGrupoById() {
        Grupo grupo = new Grupo();
        grupo.setId(1L);
        grupo.setNombre("Grupo B");

        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo));

        Optional<Grupo> result = grupoService.getGrupo(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Grupo B");
    }

    @Test
    void testCreateGrupo() {
        Grupo nuevo = new Grupo();
        nuevo.setNombre("Nuevo Grupo");

        when(grupoRepository.save(nuevo)).thenReturn(nuevo);

        Grupo result = grupoService.createGrupo(nuevo);

        assertThat(result.getNombre()).isEqualTo("Nuevo Grupo");
    }

    @Test
    void testUpdateGrupo() {
        Grupo actualizado = new Grupo();
        actualizado.setId(2L);
        actualizado.setNombre("Editado");

        when(grupoRepository.save(actualizado)).thenReturn(actualizado);

        Grupo result = grupoService.updateGrupo(actualizado);

        assertThat(result.getNombre()).isEqualTo("Editado");
    }

    @Test
    void testDeleteGrupo() {
        doNothing().when(grupoRepository).deleteById(1L);

        grupoService.deleteGrupo(1L);

        verify(grupoRepository, times(1)).deleteById(1L);
    }

}
