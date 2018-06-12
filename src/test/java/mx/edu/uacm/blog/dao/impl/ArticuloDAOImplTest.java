package mx.edu.uacm.blog.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

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
import mx.edu.uacm.blog.dao.UsuarioDAO;
import mx.edu.uacm.blog.domain.Articulo;
import mx.edu.uacm.blog.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProyectoBlogApplication.class)
public class ArticuloDAOImplTest {

	private static final Logger log = LogManager.getLogger(ArticuloDAOImplTest.class);

	@Autowired
	private ArticuloDAO articuloDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	//@Test
	@Transactional
	@Rollback(false)
	public void guardarArticulo() {
		log.debug("Estoy en la prueba 1");

		Date date = new Date();

		Usuario usuario = new Usuario();
		usuario.setNombre("uno");
		usuario.setCorreo("uno@gmail.com");
		usuario.setPassword("75548");
		usuario.setFechaAlta(date);
		usuario.setCiudad("Mexico");
		usuarioDAO.guardarUsuario(usuario);

		Articulo art = new Articulo();
		art.setTitulo("JPA vs Hibernate");
		art.setContenido("Frameworks para ORM");
		art.setFecha(date);
		art.setUrl("www.spring.org");
		art.setUsuario(usuario);
		articuloDAO.guardarArticulo(art);
	}

	//@Test
	@Transactional
	@Rollback(false)
	public void guardarArticulo2() {
		log.debug("Estoy en la prueba 2");
		Date date = new Date();
		Articulo art = new Articulo();

		art.setTitulo("Hibernate vs mybatis");
		art.setContenido("Frameworks para ORM");
		art.setFecha(date);
		art.setUrl("www.spring.org");
		art.setUsuario(usuarioDAO.obtenerUsuarioPorId(new Long(1)));
		articuloDAO.guardarArticulo(art);
	}

	@Test
	public void testObtnerTodosLosArticulos() {
		log.debug("Listado de todos los articulos");
		log.debug("\n===========================");
		List<Articulo> art = articuloDAO.obtenerArticulos();
		Assert.assertThat(art, is(not(nullValue())));
		for (Articulo articulos : art) {
			log.debug(articulos.getIdArticulo());
			log.debug(articulos.getTitulo());
			log.debug(articulos.getContenido());
			log.debug(articulos.getFecha());
			log.debug(articulos.getUrl());
		}
	}

	@Test
	public void obtenerArticuloPorId() {
		log.debug("Obtener articulos");
		Articulo us = articuloDAO.obtenerArticuloPorId(new Long(1));
		log.debug("Articulos del usuario 1"+us.getContenido());
		assertNotNull(articuloDAO);
	}

}
