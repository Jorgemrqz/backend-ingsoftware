package com.ingsoftware.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.Clase;
import com.ingsoftware.backend.services.ClaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ClaseController.class)
public class ClaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClaseService claseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllClases() throws Exception {
        Clase clase = new Clase();
        clase.setId(1L);
        clase.setAula("Aula 101");

        when(claseService.getClases()).thenReturn(Collections.singletonList(clase));

        mockMvc.perform(get("/api/clases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aula").value("Aula 101"));
    }

    @Test
    void testGetClaseById_found() throws Exception {
        Clase clase = new Clase();
        clase.setId(1L);
        clase.setAula("Laboratorio");

        when(claseService.getClase(1L)).thenReturn(Optional.of(clase));

        mockMvc.perform(get("/api/clases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aula").value("Laboratorio"));
    }

    @Test
    void testGetClaseById_notFound() throws Exception {
        when(claseService.getClase(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clases/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateClase() throws Exception {
        Clase clase = new Clase();
        clase.setAula("Nueva Aula");

        when(claseService.createClase(any(Clase.class))).thenReturn(clase);

        mockMvc.perform(post("/api/clases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clase)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.aula").value("Nueva Aula"));
    }

    @Test
    void testUpdateClase_success() throws Exception {
        Clase clase = new Clase();
        clase.setId(1L);
        clase.setAula("Editada");

        when(claseService.getClase(1L)).thenReturn(Optional.of(clase));
        when(claseService.updateClase(any(Clase.class))).thenReturn(clase);

        mockMvc.perform(put("/api/clases/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aula").value("Editada"));
    }

    @Test
    void testUpdateClase_notFound() throws Exception {
        Clase clase = new Clase();
        clase.setAula("No existe");

        when(claseService.getClase(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/clases/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clase)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteClase_success() throws Exception {
        Clase clase = new Clase();
        clase.setId(1L);

        when(claseService.getClase(1L)).thenReturn(Optional.of(clase));
        Mockito.doNothing().when(claseService).deleteClase(1L);

        mockMvc.perform(delete("/api/clases/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteClase_notFound() throws Exception {
        when(claseService.getClase(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/clases/99"))
                .andExpect(status().isNotFound());
    }
}
