package mx.edu.uacm.blog.dao;

import java.util.List;

import mx.edu.uacm.blog.domain.Articulo;

public interface ArticuloDAO {

	Articulo obtenerArticuloPorId(Long id);
	void guardarArticulo(Articulo articulo);
	List<Articulo> obtenerArticulos();
	
}
