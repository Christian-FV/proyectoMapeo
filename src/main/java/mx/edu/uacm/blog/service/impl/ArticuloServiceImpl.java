package mx.edu.uacm.blog.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.blog.dao.ArticuloDAO;
import mx.edu.uacm.blog.domain.Articulo;
import mx.edu.uacm.blog.service.ArticuloService;


@Service
public class ArticuloServiceImpl implements ArticuloService {

	
	public static final Logger log = LogManager.getLogger(ArticuloServiceImpl.class);
	
	@Autowired
	ArticuloDAO articuloDao;
	
	@Override
	public String guardarArticulo(Articulo articulo) {
		articuloDao.guardarArticulo(articulo);
		return "Articulo Guardado";
	}

	@Override
	public Articulo obtenerArticulo(Long id) {
		return articuloDao.obtenerArticuloPorId(id);
	}

	@Override
	public List<Articulo> obtenerArticulos() {
		// TODO Auto-generated method stub
		return articuloDao.obtenerArticulos();
	}

}
