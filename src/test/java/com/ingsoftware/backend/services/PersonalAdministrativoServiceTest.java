package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.PersonalAdministrativo;
import com.ingsoftware.backend.model.Usuario;
import com.ingsoftware.backend.repository.PersonalAdministrativoRepository;

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
class PersonalAdministrativoServiceTest {

    @Mock
    private PersonalAdministrativoRepository pAdministrativoRepository;

    @InjectMocks
    private PersonalAdministrativoImpl pAdministrativoService;

    @Test
    void testGetPersonalAdministrativo() {
        PersonalAdministrativo p1 = new PersonalAdministrativo();
        p1.setId(1L);
        p1.setNombres("Juan");

        PersonalAdministrativo p2 = new PersonalAdministrativo();
        p2.setId(2L);
        p2.setNombres("Ana");

        when(pAdministrativoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PersonalAdministrativo> result = pAdministrativoService.getPersonalAdministrativo();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombres()).isEqualTo("Juan");
    }

    @Test
    void testGetPersona() {
        PersonalAdministrativo persona = new PersonalAdministrativo();
        persona.setId(3L);
        persona.setApellidos("Pérez");

        when(pAdministrativoRepository.findById(3L)).thenReturn(Optional.of(persona));

        Optional<PersonalAdministrativo> result = pAdministrativoService.getPersona(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getApellidos()).isEqualTo("Pérez");
    }

    @Test
    void testCreateAdministrativo() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        PersonalAdministrativo nuevo = new PersonalAdministrativo();
        nuevo.setNombres("Carlos");
        nuevo.setCargo("Secretario");
        nuevo.setUsuario(usuario);

        when(pAdministrativoRepository.save(nuevo)).thenReturn(nuevo);

        PersonalAdministrativo result = pAdministrativoService.createAdministrativo(nuevo);

        assertThat(result.getCargo()).isEqualTo("Secretario");
        assertThat(result.getUsuario()).isEqualTo(usuario);
    }

    @Test
    void testUpdateAdministrativo() {
        PersonalAdministrativo actualizado = new PersonalAdministrativo();
        actualizado.setId(5L);
        actualizado.setApellidos("González");

        when(pAdministrativoRepository.save(actualizado)).thenReturn(actualizado);

        PersonalAdministrativo result = pAdministrativoService.updateAdministrativo(actualizado);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getApellidos()).isEqualTo("González");
    }

    @Test
    void testDeletePersonalAdministrativo() {
        Long id = 8L;

        doNothing().when(pAdministrativoRepository).deleteById(id);

        pAdministrativoService.deletePersonalAdministrativo(id);

        verify(pAdministrativoRepository, times(1)).deleteById(id);
    }
}
