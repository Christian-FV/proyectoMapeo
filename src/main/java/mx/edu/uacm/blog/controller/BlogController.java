package mx.edu.uacm.blog.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.edu.uacm.blog.domain.Articulo;
import mx.edu.uacm.blog.domain.Comentario;
import mx.edu.uacm.blog.domain.Usuario;
import mx.edu.uacm.blog.service.ArticuloService;
import mx.edu.uacm.blog.service.ComentarioService;
import mx.edu.uacm.blog.service.UsuarioService;
/**
 * 
 * @author ChristianFV
 *
 */
@Controller
public class BlogController {

	public static final Logger log = LogManager.getLogger(BlogController.class);
	
	
	@Autowired
	private ArticuloService articuloService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private HttpSession httpSession;
	
	
	@GetMapping("/")
	public String home(Model model) {
		log.debug("Entrando a home");
		List<Articulo> articulos = articuloService.obtenerArticulos();
		articulos.get(0).getUsuario().getCorreo();
		model.addAttribute("usuario", httpSession.getAttribute("userLoggedInObj"));
		model.addAttribute("articulos", articulos);
		
		return "home";	
	}
	
	@GetMapping("/autores")
	public String actores(ModelMap model) {
		log.debug("Entrando a autores");
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		log.debug("Valor articulos: " + usuarios.get(0).getArticulos().size());
		model.put("usuarios", usuarios);
		return "autores";	
	}
		
	@GetMapping("/escribirpost")
	public String escribirPost(Map<String, Object> model, Usuario usuario) {
		log.debug("Entrando a escribirPost");
		model.put("ids", usuario.getId());
		return "escribirpost";	
	}
	
	
	
	@GetMapping("/registro")
	public String registro(Map<String, Object> model) {
		log.debug("Entrando a registro");
		return "registro";
	}
	
	@PostMapping("/login")
	public ModelAndView registroUsuario(ModelMap model, Usuario usuario) {
		log.debug("Usuario: " + usuario.getNombre());
		log.debug("Se registro el usuario");
		Date date = new Date();		
		usuario.setFechaAlta(date);
		if(!usuarioService.existeUsuarioCorreo(usuario.getCorreo())) {
			String mensaje = usuarioService.guardarUsuario(usuario);
			if(mensaje != null) {
				return new ModelAndView("redirect:/login",model);
			}else {
				return new ModelAndView("redirect:/registro",model);
			}
		}else {
			model.put("error", "El correo utilizado ya esta registrado");
			return new ModelAndView("redirect:/registro",model);
		}
	}
	
	@GetMapping("/login")
	public String login(Map<String, Object> model) {
		log.debug("Entrando a login");
		return "login";
	}
	
	@PostMapping("/")
	public ModelAndView iniciarSesion(ModelMap model, Usuario usuario) {
		log.debug("Entrando a iniciarSesion");
		Usuario usuarioObtenido = usuarioService.obtenerUsuarioCorreoPass(usuario.getCorreo(), usuario.getPassword());
		
		if(usuarioObtenido != null) {
			log.debug("Entro a Login ****************");
			httpSession.setAttribute("userLoggedIn", usuarioObtenido.getId());
			httpSession.setAttribute("userLoggedInObj", usuarioObtenido);
		}else {
			model.put("error", "Usuario/Contrasena no validos");
		}
		model.addAttribute("ids", usuarioObtenido.getId());
		return new ModelAndView("redirect:/",model);
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarPost(ModelMap model, Articulo art) {
		log.debug("Entrando a guardarPost " + art.getContenido() +"******");
		
		Long idUser = (Long) httpSession.getAttribute("userLoggedIn");
		
		Usuario usuarioObtenido = usuarioService.obtenerUsuarioId(idUser);
		
		Date date = new Date();
		
		art.setUsuario(usuarioObtenido);
		art.setFecha(date);
		articuloService.guardarArticulo(art);
		log.debug("Se guardo correctamente el articulo" + art);
		return  new ModelAndView("redirect:/",model);	
	}
	
	@GetMapping("/logout")
	public String logout() {
		log.debug("Entrando a logout");
		httpSession.removeAttribute("userLoggedIn");
		return "redirect:/";
	}
	
	@GetMapping("/mostrar")
	public  String mostrarArticulo(ModelMap model, @RequestParam("articulo_id") Long idArt) {
		log.debug("Entrando a agregarComentario" + idArt);
		Articulo artObtenido = articuloService.obtenerArticulo(idArt);
		
		model.put("articulo", artObtenido);
		List<Comentario> comentariosArt = comentarioService.obtenerComentariosPorIdArticulo(idArt);
		log.debug("Comentario: " + comentariosArt.size());


		for (Comentario comentarios : comentariosArt) {
			//log.debug(((Comentario) iterable_element).getContenido());
			log.debug(comentarios.getIdComentario());
			log.debug(comentarios.getContenido());
			log.debug(comentarios.getFechaCom());
		}
		model.put("comentarios", comentariosArt);
		return  "comentar";	
	}
	
	@PostMapping("/comentar")
	public ModelAndView guardarComentario(ModelMap model, @RequestParam("articulo_id") Long idArt, Comentario comm) {
		log.debug("Entrando a guardarComentario***********");
		Articulo artObtenido = articuloService.obtenerArticulo(idArt);
		Long idUsu = (Long) httpSession.getAttribute("userLoggedIn");
		Usuario usuarioObtenido = usuarioService.obtenerUsuarioId(idUsu);
		comm.setArticuloId(artObtenido);
		comm.setUsuarioId(usuarioObtenido);
		Date fecha = new Date();
		comm.setFechaCom(fecha);
		comentarioService.guardarComentario(comm);
		
		return  new ModelAndView("redirect:/", model);
	}
	
	
	
}
