package com.um.tp4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.um.tp4.model.EstadoLibro;
import com.um.tp4.model.Libro;
import com.um.tp4.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro(1L, "123456", "Clean Code", "Robert C. Martin", EstadoLibro.DISPONIBLE);
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(libroService.obtenerTodos()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Clean Code"));
    }

    @Test
    void testCrearLibro() throws Exception {
        when(libroService.guardar(any())).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("123456"));
    }

    @Test
    void testEliminarLibro() throws Exception {
        doNothing().when(libroService).eliminar(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isOk());

        verify(libroService, times(1)).eliminar(1L);
    }
}
