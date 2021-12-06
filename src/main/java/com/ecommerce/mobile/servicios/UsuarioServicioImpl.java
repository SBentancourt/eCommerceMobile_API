package com.ecommerce.mobile.servicios;

import com.ecommerce.mobile.entidades.Usuario;
import com.ecommerce.mobile.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(String correo) {
        return usuarioRepo.findById(correo);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> obtenerUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(String correo) {
        usuarioRepo.deleteById(correo);
    }
}

// -- *** COMENTARIOS *** -- //
// -- los métodos .findById() y .deleteById() únicamente funcionan si el id es String.
