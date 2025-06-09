package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.enums.RolEnum;
import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.model.Usuario;
import com.ingsoftware.backend.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@Import(UsuarioControllerTest.MockConfig.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UsuarioService usuarioService() {
            return Mockito.mock(UsuarioService.class);
        }
    }

    @Test
    void testGetAllUsuarios() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setPassword("1234");
        usuario.setRol(RolEnum.ADMIN);
        usuario.setPerfilAcceso(new PerfilAcceso());

        Mockito.when(usuarioService.getUsuarios()).thenReturn(Collections.singletonList(usuario));

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("admin"));
    }

    @Test
    void testGetUsuarioById_found() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setRol(RolEnum.ADMIN);
        usuario.setPerfilAcceso(new PerfilAcceso());

        Mockito.when(usuarioService.getUsuario(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    void testGetUsuarioById_notFound() throws Exception {
        Mockito.when(usuarioService.getUsuario(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuario/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("nuevo");
        usuario.setPassword("pass");
        usuario.setRol(RolEnum.DOCENTE);
        usuario.setPerfilAcceso(new PerfilAcceso());

        Mockito.when(usuarioService.createUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("nuevo"));
    }

    @Test
    void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("actualizado");
        usuario.setRol(RolEnum.ADMIN);
        usuario.setPerfilAcceso(new PerfilAcceso());

        Mockito.when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("actualizado"));
    }

    @Test
    void testDeleteUsuario_found() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("delete");
        usuario.setPerfilAcceso(new PerfilAcceso());

        Mockito.when(usuarioService.getUsuario(1L)).thenReturn(Optional.of(usuario));
        Mockito.doNothing().when(usuarioService).deleteUsuario(1L);

        mockMvc.perform(delete("/api/usuario/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUsuario_notFound() throws Exception {
        Mockito.when(usuarioService.getUsuario(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/usuario/999"))
                .andExpect(status().isBadRequest());
    }
}
