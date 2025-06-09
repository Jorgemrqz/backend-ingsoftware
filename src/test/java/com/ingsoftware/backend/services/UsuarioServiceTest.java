package com.ingsoftware.backend.services;

import com.ingsoftware.backend.enums.RolEnum;
import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.model.Usuario;
import com.ingsoftware.backend.repository.UsuarioRepository;

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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioImpl usuarioService;

    @Test
    void testGetUsuarios() {
        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setUsername("admin");

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setUsername("estudiante");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> result = usuarioService.getUsuarios();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo("admin");
    }

    @Test
    void testGetUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(3L);
        usuario.setRol(RolEnum.ADMIN);

        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.getUsuario(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getRol()).isEqualTo(RolEnum.ADMIN);
    }

    @Test
    void testCreateUsuario() {
        PerfilAcceso perfil = new PerfilAcceso();
        perfil.setId(1L);

        Usuario nuevo = new Usuario();
        nuevo.setUsername("nuevo_usuario");
        nuevo.setPassword("1234");
        nuevo.setRol(RolEnum.DOCENTE);
        nuevo.setPerfilAcceso(perfil);

        when(usuarioRepository.save(nuevo)).thenReturn(nuevo);

        Usuario result = usuarioService.createUsuario(nuevo);

        assertThat(result.getUsername()).isEqualTo("nuevo_usuario");
        assertThat(result.getRol()).isEqualTo(RolEnum.DOCENTE);
    }

    @Test
    void testUpdateUsuario() {
        Usuario actualizado = new Usuario();
        actualizado.setId(5L);
        actualizado.setUsername("actualizado");

        when(usuarioRepository.save(actualizado)).thenReturn(actualizado);

        Usuario result = usuarioService.updateUsuario(actualizado);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getUsername()).isEqualTo("actualizado");
    }

    @Test
    void testDeleteUsuario() {
        Long id = 7L;

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
