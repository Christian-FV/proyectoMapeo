package mx.edu.uacm.blog.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import mx.edu.uacm.blog.dao.ArticuloDAO;
import mx.edu.uacm.blog.domain.Articulo;

@Transactional
@Repository
public class ArticuloDAOImpl implements ArticuloDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void guardarArticulo(Articulo articulo) {
		entityManager.persist(articulo);
	}

	public Articulo obtenerArticuloPorId(Long id) {
		return entityManager.find(Articulo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Articulo> obtenerArticulos() {
		return entityManager.createQuery("select a from articulo a").getResultList();
	}
	
}