package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.Horario;
import com.ingsoftware.backend.repository.HorarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorarioServiceTest {

    @Mock
    private HorarioRepository horarioRepository;

    @InjectMocks
    private HorarioImpl horarioService;

    @Test
    void testGetHorarios() {
        Horario h1 = new Horario();
        h1.setId(1L);
        h1.setDiaSemana("Lunes");

        when(horarioRepository.findAll()).thenReturn(List.of(h1));

        List<Horario> result = horarioService.getHorarios();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDiaSemana()).isEqualTo("Lunes");
    }

    @Test
    void testGetHorarioById() {
        Horario horario = new Horario();
        horario.setId(1L);
        horario.setDiaSemana("Martes");

        when(horarioRepository.findById(1L)).thenReturn(Optional.of(horario));

        Optional<Horario> result = horarioService.getHorario(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getDiaSemana()).isEqualTo("Martes");
    }

    @Test
    void testCreateHorario() {
        Horario nuevo = new Horario();
        nuevo.setDiaSemana("Miércoles");
        nuevo.setHoraInicio(LocalTime.of(9, 0));
        nuevo.setHoraFin(LocalTime.of(10, 0));

        when(horarioRepository.save(nuevo)).thenReturn(nuevo);

        Horario result = horarioService.createHorario(nuevo);

        assertThat(result.getDiaSemana()).isEqualTo("Miércoles");
    }

    @Test
    void testUpdateHorario() {
        Horario modificado = new Horario();
        modificado.setId(2L);
        modificado.setDiaSemana("Jueves");

        when(horarioRepository.save(modificado)).thenReturn(modificado);

        Horario result = horarioService.updateHorario(modificado);

        assertThat(result.getDiaSemana()).isEqualTo("Jueves");
    }

    @Test
    void testDeleteHorario() {
        doNothing().when(horarioRepository).deleteById(1L);

        horarioService.deleteHorario(1L);

        verify(horarioRepository, times(1)).deleteById(1L);
    }

}
