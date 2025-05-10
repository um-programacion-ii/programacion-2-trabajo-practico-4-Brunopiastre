package com.um.tp4.service;

import com.um.tp4.model.EstadoUsuario;
import com.um.tp4.model.Usuario;
import com.um.tp4.repository.UsuarioRepository;
import com.um.tp4.exception.RecursoNoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void testGuardarUsuario() {
        Usuario usuario = new Usuario(null, "bruno", "bruno@email.com", EstadoUsuario.ACTIVO);
        Usuario usuarioGuardado = new Usuario(1L, "bruno", "bruno@email.com", EstadoUsuario.ACTIVO);

        when(usuarioRepository.save(usuario)).thenReturn(usuarioGuardado);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("bruno", resultado.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testBuscarPorEmail_Existe() {
        Usuario usuario = new Usuario(1L, "bruno", "bruno@email.com", EstadoUsuario.ACTIVO);

        when(usuarioRepository.findByEmail("bruno@email.com")).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorEmail("bruno@email.com");

        assertNotNull(resultado);
        assertEquals("bruno", resultado.getNombre());
        verify(usuarioRepository).findByEmail("bruno@email.com");
    }

    @Test
    void testBuscarPorEmail_NoExiste() {
        when(usuarioRepository.findByEmail("noexiste@email.com")).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () ->
                usuarioService.buscarPorEmail("noexiste@email.com"));
    }

    @Test
    void testObtenerTodos() {
        Usuario usuario1 = new Usuario(1L, "A", "a@email.com", EstadoUsuario.ACTIVO);
        Usuario usuario2 = new Usuario(2L, "B", "b@email.com", EstadoUsuario.INACTIVO);
        List<Usuario> lista = List.of(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(lista);

        List<Usuario> resultado = usuarioService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }
}
