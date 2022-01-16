package com.prog.ar.usuarios.controllers;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prog.ar.usuarios.dao.UsuarioDao;
import com.prog.ar.usuarios.models.Usuario;
import com.prog.ar.usuarios.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private JWTUtil jwtUtil;

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
	public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
		if (!validarToken(token)) {
			return null;
		}

		return usuarioDao.geUsuarios();
	}

	private boolean validarToken(String token) {
		String usuarioId = jwtUtil.getKey(token);
		return usuarioId != null;
	}

	@RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
		usuario.setPassword(hash);

		usuarioDao.registrar(usuario);

	}

	@RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
	public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
		if (!validarToken(token)) {
			return;
		}

		usuarioDao.eliminar(id);

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

}
