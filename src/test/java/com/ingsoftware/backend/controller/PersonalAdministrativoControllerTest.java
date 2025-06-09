package com.ingsoftware.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingsoftware.backend.model.PersonalAdministrativo;
import com.ingsoftware.backend.services.PersonalAdministrativoService;

@WebMvcTest(PersonalAdministrativoController.class)
@Import(PersonalAdministrativoControllerTest.MockConfig.class)
public class PersonalAdministrativoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonalAdministrativoService personalAdministrativoService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PersonalAdministrativoService personalAdministrativoService() {
            return Mockito.mock(PersonalAdministrativoService.class);
        }
    }

    @Test
    void testGetAll() throws Exception {
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setId(1L);
        p.setNombres("María");
        p.setApellidos("Pérez");
        p.setCargo("Secretaria");

        Mockito.when(personalAdministrativoService.getPersonalAdministrativo()).thenReturn(Collections.singletonList(p));

        mockMvc.perform(get("/api/personal-administrativo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombres").value("María"));
    }

    @Test
    void testGetById_found() throws Exception {
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setId(1L);
        p.setNombres("Carlos");

        Mockito.when(personalAdministrativoService.getPersona(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/personal-administrativo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Carlos"));
    }

    @Test
    void testGetById_notFound() throws Exception {
        Mockito.when(personalAdministrativoService.getPersona(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/personal-administrativo/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setNombres("Lucía");
        p.setApellidos("Gómez");
        p.setCargo("Contadora");

        Mockito.when(personalAdministrativoService.createAdministrativo(any(PersonalAdministrativo.class))).thenReturn(p);

        mockMvc.perform(post("/api/personal-administrativo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Lucía"));
    }

    @Test
    void testUpdate() throws Exception {
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setId(1L);
        p.setNombres("Modificado");

        Mockito.when(personalAdministrativoService.updateAdministrativo(any(PersonalAdministrativo.class))).thenReturn(p);

        mockMvc.perform(put("/api/personal-administrativo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Modificado"));
    }

    @Test
    void testDelete_found() throws Exception {
        PersonalAdministrativo p = new PersonalAdministrativo();
        p.setId(1L);

        Mockito.when(personalAdministrativoService.getPersona(1L)).thenReturn(Optional.of(p));
        Mockito.doNothing().when(personalAdministrativoService).deletePersonalAdministrativo(1L);

        mockMvc.perform(delete("/api/personal-administrativo/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete_notFound() throws Exception {
        Mockito.when(personalAdministrativoService.getPersona(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/personal-administrativo/999"))
                .andExpect(status().isBadRequest());
    }
}
