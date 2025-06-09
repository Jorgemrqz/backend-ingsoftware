package com.ingsoftware.backend.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ingsoftware.backend.model.Usuario;
import com.ingsoftware.backend.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioImpl usuarioService;

    @Test
    void testGetUsuarioById() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1L);
        mockUsuario.setUsername("admin");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockUsuario));

        Optional<Usuario> result = usuarioService.getUsuario(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("admin");
    }

    @Test
    void testGetUsuarios() {
        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setUsername("admin");

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setUsername("test");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> usuarios = usuarioService.getUsuarios();

        assertThat(usuarios).hasSize(2);
        assertThat(usuarios.get(0).getUsername()).isEqualTo("admin");
    }

    @Test
    void testCreateUsuario() {
        Usuario nuevo = new Usuario();
        nuevo.setUsername("nuevo");

        when(usuarioRepository.save(nuevo)).thenReturn(nuevo);

        Usuario creado = usuarioService.createUsuario(nuevo);

        assertThat(creado.getUsername()).isEqualTo("nuevo");
    }
}
