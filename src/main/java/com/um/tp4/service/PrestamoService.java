package com.um.tp4.service;

import com.um.tp4.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo guardar(Prestamo prestamo);
    List<Prestamo> obtenerTodos();
    Prestamo buscarPorId(Long id);
    void eliminar(Long id);
}
