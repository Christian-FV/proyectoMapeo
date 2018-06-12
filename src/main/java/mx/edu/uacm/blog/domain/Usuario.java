package mx.edu.uacm.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ColumnTransformer;
/**
 * 
 * @author ChristianFV
 *
 */

@Entity(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sequence_hibernate", table = "sequence_hibernate", pkColumnName = "ent_name", valueColumnName = "ent_val", pkColumnValue = "usuario", initialValue = 0, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence_hibernate")
	private Long idUsuario;

	@Column(nullable = false)
	private String nombre;

	@Column(unique = true, nullable = false)
	private String correo;

	@Column(nullable = false)
	@ColumnTransformer(write = " MD5(?) ")
	private String password;

	@Column(nullable = false)
	private Date fechaAlta;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Articulo> articulos = new ArrayList<Articulo>();

	@OneToMany(mappedBy="usuarioId", cascade=CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	private String ciudad;

	/**
	 * @return the id
	 */
	public Long getId() {
		return idUsuario;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the idUsuario
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the articulos
	 */
	public List<Articulo> getArticulos() {
		return articulos;
	}

	/**
	 * @param articulos the articulos to set
	 */
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	
}
