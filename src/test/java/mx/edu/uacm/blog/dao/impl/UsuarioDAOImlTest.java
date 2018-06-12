package mx.edu.uacm.blog.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.blog.ProyectoBlogApplication;
import mx.edu.uacm.blog.dao.UsuarioDAO;
import mx.edu.uacm.blog.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProyectoBlogApplication.class)
public class UsuarioDAOImlTest {

	private static final Logger log = LogManager.getLogger(UsuarioDAOImlTest.class);

	@Autowired
	private UsuarioDAO usuarioDAO;

	//@Test
	public void guardarUsuario() {
		log.debug("Estoy en la prueba 1");
		Date date = new Date();
		Usuario usuario = new Usuario();
		usuario.setNombre("uno");
		usuario.setCorreo("tres@gmail.com");
		usuario.setPassword("75548");
		usuario.setFechaAlta(date);
		usuario.setCiudad("Mexico");
		usuarioDAO.guardarUsuario(usuario);
		assertNotNull(usuarioDAO);
	}

	//@Test
	public void guardarUsuario2() {
		log.debug("Estoy en la prueba 2");
		Date date = new Date();
		Usuario usuario = new Usuario();
		usuario.setNombre("otromas");
		usuario.setCorreo("cuatro@gmail.com");
		usuario.setPassword("75548");
		usuario.setFechaAlta(date);
		usuario.setCiudad("Mexico");
		usuarioDAO.guardarUsuario(usuario);
		assertNotNull(usuarioDAO);
	}
	
	//@Test
	public void testObteneUsarios() {
		log.debug("Listado de todos los clientes");
		log.debug("\n===========================");
		List<Usuario> clientes = usuarioDAO.obtenerUsuarios();
		Assert.assertThat(clientes, is(not(nullValue())));
		for (Usuario cl : clientes) {
			log.debug(cl.getId());
			log.debug(cl.getNombre());
			log.debug(cl.getCorreo());
			log.debug(cl.getPassword());
			log.debug(cl.getFechaAlta());
			log.debug(cl.getCiudad());
		}
	}
	
	@Test
	public void obtenerUsuarioPorId() {
		log.debug("Obtener usuario");
		Usuario us = usuarioDAO.obtenerUsuarioPorId(new Long(2));
		log.debug("id "+us.getId());
		log.debug("nombre: "+us.getNombre());
		log.debug("correo: "+us.getCorreo());
		log.debug("pass: "+us.getPassword());
		log.debug("fecha: "+us.getFechaAlta());
		log.debug("ciudad: "+us.getCiudad());
		assertNotNull(us.getCorreo());
	}

	@Test
	public void existeUsuarioPorCorreo() {
		log.debug("Obtener usuario por correo");
		String correo = "cuatro@gmail.com";
		String pass = "75548";
		Usuario us = usuarioDAO.obtenerUsuarioPorCorreoPassword(correo, pass);
		assertNotNull(us);
		log.debug("id "+us.getId());
		log.debug("nombre: "+us.getNombre());
		log.debug("correo: "+us.getCorreo());
		log.debug("pass: "+us.getPassword());
		log.debug("fecha: "+us.getFechaAlta());
		log.debug("ciudad: "+us.getCiudad());
	}
	
	@Test
	public void contarComentarios() {
		String correo = "once@gmail.com";
		int comm = usuarioDAO.obtenerNumComentariosPorUsuario(correo);
		log.debug("Comentarios: " + comm);
		assertNotNull(comm);
	}

	@Test
	public void contarArticulos() {
		String correo = "once@gmail.com";
		int comm = usuarioDAO.obtenerNumArticulosPorUsuario(correo);
		log.debug("Articulos: " + comm);
		assertNotNull(comm);
	}

	@Test
	public void existeUsuario() {
		String correo = "diez@gmail.com";
		boolean comm = usuarioDAO.existeUsuario(correo);
		log.debug("Existe: " + comm);
		assertNotNull(comm);
	}
}
