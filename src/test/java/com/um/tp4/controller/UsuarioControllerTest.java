package com.um.tp4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.um.tp4.model.EstadoUsuario;
import com.um.tp4.model.Usuario;
import com.um.tp4.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "Bruno", "bruno@email.com", EstadoUsuario.ACTIVO);
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(usuarioService.obtenerTodos()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre").value("Bruno"));
    }

    @Test
    void testGuardar() throws Exception {
        Usuario nuevo = new Usuario(null, "Nuevo", "nuevo@email.com", EstadoUsuario.ACTIVO);
        Usuario guardado = new Usuario(2L, "Nuevo", "nuevo@email.com", EstadoUsuario.ACTIVO);

        when(usuarioService.guardar(nuevo)).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
