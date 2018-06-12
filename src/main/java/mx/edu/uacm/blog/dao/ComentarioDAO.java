package mx.edu.uacm.blog.dao;

import java.util.List;

import mx.edu.uacm.blog.domain.Comentario;

public interface ComentarioDAO {
	
	Comentario obtenerComentarioPorId(Long id);
	void guardarComentario(Comentario comentario);
	List<Comentario> obtenerComentarios();
	List<Comentario> obtenerComentariosporIdArticulo(Long id);
	
}
