package mx.edu.uacm.blog.dao.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import mx.edu.uacm.blog.dao.UsuarioDAO;
import mx.edu.uacm.blog.domain.Usuario;

@Transactional
@Repository
public class UsuarioDAOIml implements UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceContext
	private EntityManager em;

	public void guardarUsuario(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public Usuario obtenerUsuarioPorId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuarios() {
		return entityManager.createQuery("select u from usuario u").getResultList();
	}

	@Override
	public Usuario obtenerUsuarioPorCorreoPassword(String correo, String password) {
		Usuario usuario = new Usuario();
		try {
			usuario = (Usuario) em.createNativeQuery("SELECT * FROM USUARIO WHERE correo = :correo and password=MD5(:password)", Usuario.class)
					.setParameter("correo", correo).setParameter("password", password).getSingleResult();
		} catch (NoResultException ex) {
			usuario = null;
		}
		return usuario;
	}
	
	@Override
	public int obtenerNumComentariosPorUsuario(String correo) {
		int result = 0;
		try {
			if (existeUsuario(correo)) {
				BigInteger comentarios = new BigInteger("" + em
						.createNativeQuery("SELECT count(*) FROM comentario "
								+ "WHERE usuario_id = (SELECT idUsuario FROM usuario WHERE correo = :correo)")
						.setParameter("correo", correo).getSingleResult());
				result = comentarios.intValue();
			} else {
				result = -1;
			}
		} catch (NoResultException ex) {
			result = -1;
		}
		return result;
	}

	@Override
	public int obtenerNumArticulosPorUsuario(String correo) {
		int result = 0;
		try {
			if (existeUsuario(correo)) {
				BigInteger comentarios = new BigInteger("" + em
						.createNativeQuery("SELECT count(*) FROM articulo "
								+ "WHERE usuario_id = (SELECT idUsuario FROM usuario WHERE correo = :correo)")
						.setParameter("correo", correo).getSingleResult());
				result = comentarios.intValue();
			}
		} catch (NoResultException ex) {
			result = -1;
		}
		return result;
	}

	@Override
	public boolean existeUsuario(String correo) {
		String result = null;
		boolean bool = false;
		try {
			result = (String) em.createNativeQuery("SELECT u.correo FROM usuario u WHERE u.correo = :correo")
					.setParameter("correo", correo).getSingleResult();
			if (result != null) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (NoResultException ex) {
			return bool;
		}
		return bool;
	}

}