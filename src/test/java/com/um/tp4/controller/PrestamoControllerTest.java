package com.um.tp4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.um.tp4.model.EstadoLibro;
import com.um.tp4.model.Libro;
import com.um.tp4.model.Prestamo;
import com.um.tp4.model.Usuario;
import com.um.tp4.service.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
public class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        Libro libro = new Libro(1L, "123", "Libro Test", "Autor", EstadoLibro.DISPONIBLE);
        Usuario usuario = new Usuario(1L, "Bruno", "bruno@email.com", null);
        prestamo = new Prestamo(1L, libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7));
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(prestamoService.obtenerTodos()).thenReturn(List.of(prestamo));

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGuardar() throws Exception {
        Prestamo nuevo = new Prestamo(null, prestamo.getLibro(), prestamo.getUsuario(),
                LocalDate.now(), LocalDate.now().plusDays(7));
        Prestamo guardado = new Prestamo(2L, prestamo.getLibro(), prestamo.getUsuario(),
                LocalDate.now(), LocalDate.now().plusDays(7));

        when(prestamoService.guardar(nuevo)).thenReturn(guardado);

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
