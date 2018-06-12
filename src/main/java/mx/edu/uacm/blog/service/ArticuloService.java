package mx.edu.uacm.blog.service;

import java.util.List;

import mx.edu.uacm.blog.domain.Articulo;

public interface ArticuloService {

	/**
	 * Metodo para guardar un articulo en la BD
	 * @param articulo
	 */
	
	public String guardarArticulo(Articulo articulo);
	
	/**
	 * Metodo que obtiene un articulo por medio del id
	 * @param id
	 * @return
	 */
	
	public Articulo obtenerArticulo(Long id);
	
	/**
	 * Metodo que obtiene todos los articulos
	 * @return
	 */
	
	public List<Articulo> obtenerArticulos();
	
}
