package mx.edu.uacm.blog.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.blog.dao.UsuarioDAO;
import mx.edu.uacm.blog.domain.Usuario;
import mx.edu.uacm.blog.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	public UsuarioDAO usuarioDao;
	
	public static final Logger log = LogManager.getLogger(UsuarioServiceImpl.class);
	
	@Override
	public String guardarUsuario(Usuario usuario) {
		usuarioDao.guardarUsuario(usuario);
		return "Usuario guardado";
	}

	@Override
	public Usuario obtenerUsuarioId(Long id) {
		return usuarioDao.obtenerUsuarioPorId(id);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioDao.obtenerUsuarios();
	}

	@Override
	public Usuario obtenerUsuarioCorreoPass(String correo, String password) {
		return usuarioDao.obtenerUsuarioPorCorreoPassword(correo, password);
	}

	@Override
	public int obtenerNumComentiosPorUsuario(String correo) {
		return usuarioDao.obtenerNumComentariosPorUsuario(correo);
	}

	@Override
	public int obtenerNumArticulosPorUsuario(String correo) {
		return usuarioDao.obtenerNumArticulosPorUsuario(correo);
	}

	@Override
	public boolean existeUsuarioCorreo(String correo) {
		return usuarioDao.existeUsuario(correo);
	}

	
}
