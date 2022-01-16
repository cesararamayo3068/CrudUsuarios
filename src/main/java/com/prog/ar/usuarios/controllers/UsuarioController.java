package com.prog.ar.usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prog.ar.usuarios.dao.UsuarioDao;
import com.prog.ar.usuarios.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
	public Usuario getUsuario(@PathVariable Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre("Cesar");
		usuario.setApellido("Aramayo");
		usuario.setEmail("cesar_aramayo@hotmail.es");
		usuario.setTelefono("454546777");
		return usuario;
	}

	@RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios() {
		return usuarioDao.geUsuarios();
	}

	@RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
		usuario.setPassword(hash);
		
		usuarioDao.registrar(usuario);

	}

	@RequestMapping(value = "usuario123")
	public Usuario editar() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Cesar");
		usuario.setApellido("Aramayo");
		usuario.setEmail("cesar_aramayo@hotmail.es");
		usuario.setTelefono("454546777");
		return usuario;
	}

	@RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable Long id) {
		usuarioDao.eliminar(id);

	}

	@RequestMapping(value = "usuario564")
	public Usuario buscar() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Cesar");
		usuario.setApellido("Aramayo");
		usuario.setEmail("cesar_aramayo@hotmail.es");
		usuario.setTelefono("454546777");
		return usuario;
	}

}
