package com.um.tp4.service;

import java.util.Optional;
import com.um.tp4.model.EstadoLibro;
import com.um.tp4.model.Libro;
import com.um.tp4.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void testGuardar() {
        Libro libro = new Libro(null, "123-456", "Título", "Autor", EstadoLibro.DISPONIBLE);
        Libro libroGuardado = new Libro(1L, "123-456", "Título", "Autor", EstadoLibro.DISPONIBLE);

        when(libroRepository.save(libro)).thenReturn(libroGuardado);

        Libro resultado = libroService.guardar(libro);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(libroRepository).save(libro);
    }

    @Test
    void testBuscarPorIsbn() {
        String isbn = "123-456";
        Libro libro = new Libro(1L, isbn, "Título", "Autor", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libro)); // sin Optional

        Libro resultado = libroService.buscarPorIsbn(isbn);

        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void testActualizar() {
        Long id = 1L;
        Libro libro = new Libro(id, "123-456", "Nuevo Título", "Nuevo Autor", EstadoLibro.DISPONIBLE);

        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.save(libro)).thenReturn(libro);

        Libro actualizado = libroService.actualizar(id, libro);

        assertNotNull(actualizado);
        assertEquals("Nuevo Título", actualizado.getTitulo());
        verify(libroRepository).save(libro);
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(libroRepository).deleteById(id);

        libroService.eliminar(id);

        verify(libroRepository).deleteById(id);
    }
}
