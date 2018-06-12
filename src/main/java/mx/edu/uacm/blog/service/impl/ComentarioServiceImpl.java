package mx.edu.uacm.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.blog.dao.ComentarioDAO;
import mx.edu.uacm.blog.domain.Comentario;
import mx.edu.uacm.blog.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	public ComentarioDAO comentarioDao;
	
	@Override
	public Comentario obtenerComentarioId(Long id) {
		return comentarioDao.obtenerComentarioPorId(id);
	}

	@Override
	public String guardarComentario(Comentario comentario) {
		comentarioDao.guardarComentario(comentario);
		return "Comentario guardado";
	}

	@Override
	public List<Comentario> obtenerComentarios() {
		return comentarioDao.obtenerComentarios();
	}

	@Override
	public List<Comentario> obtenerComentariosPorIdArticulo(Long idArti) {
		return comentarioDao.obtenerComentariosporIdArticulo(idArti);
	}

}
