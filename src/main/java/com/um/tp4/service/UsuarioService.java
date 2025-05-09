package com.um.tp4.service;

import com.um.tp4.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorEmail(String email);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}
