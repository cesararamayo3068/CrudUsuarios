package com.prog.ar.usuarios.dao;

import java.util.List;

import com.prog.ar.usuarios.models.Usuario;

public interface UsuarioDao {
	
	List<Usuario>geUsuarios();

	void eliminar(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
