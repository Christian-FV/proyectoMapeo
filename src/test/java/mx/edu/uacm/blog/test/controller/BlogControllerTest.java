package mx.edu.uacm.blog.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sun.org.apache.bcel.internal.generic.NEW;

import mx.edu.uacm.blog.ProyectoBlogApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ProyectoBlogApplication.class)
@AutoConfigureMockMvc
public class BlogControllerTest {

	private static final Logger log = LogManager.getLogger(BlogControllerTest.class);
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void probarHome() throws Exception {
		log.debug("Entrando a probar Home");
		mvc.perform(get("/")).andExpect(view().name("home"));
	}
	
	@Test
	public void probarRegistro() throws Exception {
		log.debug("Entrando a probar Registro");
		mvc.perform(get("/registro")).andExpect(view().name("registro"));
	}
	
	@Test
	public void probarLogin() throws Exception {
		log.debug("Entrando a probar Login");
		mvc.perform(get("/registro")).andExpect(view().name("registro"));
	}
	
	@Test
	public void probarInicioSesion() throws Exception {
		log.debug("Entrando a probarInicioSesion");
		//mvc.perform(post("/").param("correo", "christian@hotmail.com").param("password", "12345").session("userLoggedIn")).andExpect(view().name("redirect:/")).andExpect(model().attributeExists("userLoggedIn"));
	
		ResultActions ra = this.mvc.perform(post("/").param("correo", "tres@gmail.com").param("password", "75548")).andExpect(view().name("redirect:/"));
		
		MvcResult result = ra.andReturn();
		
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		
		RequestBuilder era = MockMvcRequestBuilders.post("/").session(session);
		
		session.getAttribute("userLoggedIn");
		log.debug("Session: ***" + session.getAttribute("userLoggedIn"));
		
		assertThat(session.getAttribute("userLoggedIn"), is(not(nullValue())));
		
	}
	
	
	
	//@Test
	public void registrarUsuarioTest() throws Exception {
		log.debug("Entrando a registrarUsuarioTest");
		
		mvc.perform(post("/registro").param("nombre", "christian").param("ciudad", "CDMX").param("email", "email@email.com")
				                   .param("password", "12345")).andExpect(view().name("login"));
		
	}
	
	@Test
	public void testGuardarArticulo() throws Exception {
		log.debug("Entrando a testGuardarArticulo");
			
		HashMap<String, Object> seesionattri = new HashMap<String, Object>();
		seesionattri.put("userLoggedIn", new Long(1));
		
		mvc.perform(post("/guardar").sessionAttrs(seesionattri).param("titulo", "ORM").param("url", " ").param("contenido", "Prueba 5")).andExpect(view().name("redirect:/"));
		
	}
}
