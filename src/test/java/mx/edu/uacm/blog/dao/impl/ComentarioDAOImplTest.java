package mx.edu.uacm.blog.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import mx.edu.uacm.blog.ProyectoBlogApplication;
import mx.edu.uacm.blog.dao.ArticuloDAO;
import mx.edu.uacm.blog.dao.ComentarioDAO;
import mx.edu.uacm.blog.dao.UsuarioDAO;
import mx.edu.uacm.blog.domain.Articulo;
import mx.edu.uacm.blog.domain.Comentario;
import mx.edu.uacm.blog.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProyectoBlogApplication.class)
public class ComentarioDAOImplTest {

	private static final Logger log = LogManager.getLogger(ComentarioDAOImplTest.class);

	@Autowired
	private ComentarioDAO comentarioDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ArticuloDAO articuloDAO;

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void guardarComentario() {
		log.debug("Estoy en la prueba guardar comentario");
		Date date = new Date();
		Comentario c = new Comentario();

		Usuario usuario = new Usuario();
		usuario.setNombre("uno");
		usuario.setCorreo("dos@gmail.com");
		usuario.setPassword("75548");
		usuario.setFechaAlta(date);
		usuario.setCiudad("Mexico");
		usuarioDAO.guardarUsuario(usuario);

		Articulo art = new Articulo();
		art.setTitulo("ORM");
		art.setContenido("Mapeo Objeto Relacional ..");
		art.setFecha(date);
		art.setUrl("www.spring.org");
		art.setUsuario(usuario);
		articuloDAO.guardarArticulo(art);

		c.setContenido("Excelente articulo");
		c.setFechaCom(date);
		c.setUsuarioId(usuario);
		c.setArticuloId(art);
		comentarioDAO.guardarComentario(c);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void guardarComentario2() {
		log.debug("Estoy en la prueba guardar comentario");
		Date date = new Date();
		Comentario c = new Comentario();
		c.setContenido("Puede mejorar");
		c.setFechaCom(date);
		c.setUsuarioId(usuarioDAO.obtenerUsuarioPorId(new Long(2)));
		c.setArticuloId(articuloDAO.obtenerArticuloPorId(new Long(2)));
		comentarioDAO.guardarComentario(c);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void guardarComentario3() {
		log.debug("Estoy en la prueba guardar comentario 3");
		Date date = new Date();
		Comentario c = new Comentario();
		c.setContenido("mybatis es mas facil que hibernate");
		c.setFechaCom(date);
		c.setUsuarioId(usuarioDAO.obtenerUsuarioPorId(new Long(2)));
		c.setArticuloId(articuloDAO.obtenerArticuloPorId(new Long(2)));
		comentarioDAO.guardarComentario(c);
	}

	//@Test
	public void testObtnerTodosLosComentarios() {
		log.debug("Listado de todos los comentarios");
		log.debug("\n===========================");
		List<Comentario> commen = comentarioDAO.obtenerComentarios();
		Assert.assertThat(commen, is(not(nullValue())));
		for (Comentario comentarios : commen) {
			log.debug(comentarios.getIdComentario());
			log.debug(comentarios.getContenido());
			log.debug(comentarios.getFechaCom());
		}
	}

	//@Test
	public void obtenerComentarioPorId() {
		log.debug("Obtener articulos");
		Comentario comentarios = comentarioDAO.obtenerComentarioPorId(new Long(1));
		log.debug(comentarios.getContenido());
		assertNotNull(comentarioDAO);
	}

	//@Test
	public void testObtnerTodosLosComentariosPorArt() {
		log.debug("Listado de todos los comentarios por articulo");
		List<Comentario> commen = comentarioDAO.obtenerComentariosporIdArticulo(new Long(2));
		
		log.debug("Lista comm: " + commen.size());

		for (Comentario comentarios : commen) {
			//log.debug(((Comentario) iterable_element).getContenido());
			log.debug(comentarios.getIdComentario());
			log.debug(comentarios.getContenido());
			log.debug(comentarios.getFechaCom());
		}
		
	}

}
