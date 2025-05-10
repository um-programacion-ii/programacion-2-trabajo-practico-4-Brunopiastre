package com.um.tp4.service;

import com.um.tp4.exception.RecursoNoEncontradoException;
import com.um.tp4.model.EstadoLibro;
import com.um.tp4.model.Libro;
import com.um.tp4.model.Usuario;
import com.um.tp4.model.Prestamo;
import com.um.tp4.repository.PrestamoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceImplTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void testGuardarPrestamo() {
        Prestamo prestamo = generarPrestamo(null);
        Prestamo prestamoGuardado = generarPrestamo(1L);

        when(prestamoRepository.save(prestamo)).thenReturn(prestamoGuardado);

        Prestamo resultado = prestamoService.guardar(prestamo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void testBuscarPorId_Existe() {
        Prestamo prestamo = generarPrestamo(1L);
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(prestamoRepository).findById(1L);
    }

    @Test
    void testBuscarPorId_NoExiste() {
        when(prestamoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () ->
                prestamoService.buscarPorId(99L));
    }

    @Test
    void testObtenerTodos() {
        Prestamo p1 = generarPrestamo(1L);
        Prestamo p2 = generarPrestamo(2L);
        when(prestamoRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Prestamo> resultado = prestamoService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(prestamoRepository).findAll();
    }

    @Test
    void testEliminar() {
        doNothing().when(prestamoRepository).deleteById(1L);

        prestamoService.eliminar(1L);

        verify(prestamoRepository).deleteById(1L);
    }

    private Prestamo generarPrestamo(Long id) {
        Libro libro = new Libro(10L, "999", "Libro test", "Autor", EstadoLibro.DISPONIBLE);
        Usuario usuario = new Usuario(5L, "Bruno", "bruno@email.com", null);
        return new Prestamo(id, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7));
    }
}
