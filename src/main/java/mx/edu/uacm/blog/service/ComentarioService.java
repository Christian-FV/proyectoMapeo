package mx.edu.uacm.blog.service;

import java.util.List;

import mx.edu.uacm.blog.domain.Comentario;

public interface ComentarioService {

	/**
	 * Metodo que obtiene los comentarios por id
	 * @param id
	 * @return
	 */
	public Comentario obtenerComentarioId(Long id);
	
	/**
	 * Metodo para guardar un comentario BD
	 * @param comentario
	 * @return
	 */
	public String guardarComentario(Comentario comentario);
	
	/**
	 * Metodo que obtiene todos los comentarios
	 * @return
	 */
	public List<Comentario> obtenerComentarios();
	/**
	 * Metodo que obtiene los comentarios de un articulo
	 * @param idArti
	 * @return
	 */
	public List<Comentario> obtenerComentariosPorIdArticulo(Long idArti);
}
