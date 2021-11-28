package com.ecommerce.mobile.controladores;

import com.ecommerce.mobile.entidades.Usuario;
import com.ecommerce.mobile.servicios.UsuarioServicio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // -- Insertar un nuevo usuario
    @PostMapping("/agregar")
    public ResponseEntity<?> setUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.guardarUsuario(usuario));
    }

    // -- Obtener un usuario determinado por id
    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getUsuarioPorId(@PathVariable(value = "id") String correo){
        Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(correo);
        if (!usuario.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // -- Actualizar información de un determinado usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> setInfoUsuario(@RequestBody Usuario infoUsuario, @PathVariable(value = "id") String correo){
        Optional<Usuario> usuarioObtenido = usuarioServicio.obtenerUsuarioPorId(correo);
        if (!usuarioObtenido.isPresent()){
            return ResponseEntity.notFound().build();
        }

        // -- Aca actualizo la informacion del usuario
        BeanUtils.copyProperties(infoUsuario, usuarioObtenido.get());   // -- Esto copia todos los valores de infoUsuario a usuarioObtenido

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.guardarUsuario(usuarioObtenido.get()));
    }

    // -- Eliminar determinado usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") String correo){
        if (!usuarioServicio.obtenerUsuarioPorId(correo).isPresent()){
            return ResponseEntity.notFound().build();
        }

        usuarioServicio.eliminarUsuario(correo);
        return ResponseEntity.ok().build();
    }

    // -- Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getUsuarios(){
        // -- Se recorre el objeto Iterable (usuarioServicio.obtenerUsuarios()) y se pasa a tipo List
        List<Usuario> usuarios = StreamSupport.stream(usuarioServicio.obtenerUsuarios().spliterator(), false)
                                                .collect(Collectors.toList());

        return usuarios;
    }

}
