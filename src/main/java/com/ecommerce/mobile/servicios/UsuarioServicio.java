package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Usuario;

import java.util.Optional;

public interface UsuarioServicio {
    public Optional<Usuario> obtenerUsuarioPorId(String correo);
    public Iterable<Usuario> obtenerUsuarios();
    public Usuario guardarUsuario(Usuario usuario);
    public void eliminarUsuario(String correo);
}
