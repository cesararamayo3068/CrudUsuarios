package com.prog.ar.usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prog.ar.usuarios.dao.UsuarioDao;
import com.prog.ar.usuarios.models.Usuario;
import com.prog.ar.usuarios.utils.JWTUtil;

@RestController
public class AuthController {
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public String login(@RequestBody Usuario usuario) {
		Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

		if (usuarioLogueado != null) {

			String tokenJwt = jwtUtil.create(String.valueOf(usuario.getId()), usuarioLogueado.getEmail());

			return tokenJwt;
		}

		return "FAIL";
	}

}
