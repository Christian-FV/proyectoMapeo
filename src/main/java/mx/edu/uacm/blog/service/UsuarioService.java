package mx.edu.uacm.blog.service;

import java.util.List;

import mx.edu.uacm.blog.domain.Usuario;

public interface UsuarioService {

	public String guardarUsuario(Usuario usuario);
	
	public Usuario obtenerUsuarioId(Long id);
	
	public List<Usuario> obtenerUsuarios();
	
	public Usuario obtenerUsuarioCorreoPass(String correo, String password);
	
	public int obtenerNumComentiosPorUsuario(String correo);
	
	public int obtenerNumArticulosPorUsuario(String correo);
	
	public boolean existeUsuarioCorreo(String correo);
	
	
}
