package mx.edu.uacm.blog.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mx.edu.uacm.blog.dao.ComentarioDAO;
import mx.edu.uacm.blog.domain.Comentario;

@Transactional
@Repository
public class ComentarioDAOImpl implements ComentarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Comentario obtenerComentarioPorId(Long id) {
		return entityManager.find(Comentario.class, id);
	}

	@Override
	public void guardarComentario(Comentario comentario) {
		entityManager.persist(comentario);
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> obtenerComentarios() {
		return entityManager.createQuery("select c from comentario c").getResultList();
	}

	public List<Comentario> obtenerComentariosporIdArticulo(Long id) {
		/*return (ArrayList<Comentario>) entityManager.createNativeQuery("select * from comentario where articulo_id = :id").
				setParameter("id", id).getResultList();
		*/
		// TODO Auto-generated method stub
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();

				CriteriaQuery<Comentario> consulta = cb.createQuery(Comentario.class);

				// Traete todos los empleados de la consulta
				Root<Comentario> comentarios = consulta.from(Comentario.class);

				// predicados = condiciones a nuestra query
				Predicate predicado = null;

				if (id != null) {
					predicado = cb.equal(comentarios.get("articuloId"), id);
				} else {
					predicado = cb.equal(comentarios.get("articuloId"), 0);
				}

				Predicate idArticulo = cb.or(predicado);

				consulta.select(comentarios).where(idArticulo);

				return entityManager.createQuery(consulta).getResultList();
	}
}