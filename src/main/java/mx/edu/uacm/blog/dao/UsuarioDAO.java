package mx.edu.uacm.blog.dao;

import java.util.List;

import mx.edu.uacm.blog.domain.Usuario;

public interface UsuarioDAO {

	void guardarUsuario(Usuario usuario);
	Usuario obtenerUsuarioPorId(Long id);
	Usuario obtenerUsuarioPorCorreoPassword(String correo, String password);
	List<Usuario> obtenerUsuarios();
	int obtenerNumComentariosPorUsuario(String correo);
	int obtenerNumArticulosPorUsuario(String correo);
	boolean existeUsuario(String correo);
}
