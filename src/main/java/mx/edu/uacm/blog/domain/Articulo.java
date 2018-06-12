package mx.edu.uacm.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;


@Entity(name="articulo")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sequence_hibernate", table = "sequence_hibernate", pkColumnName = "ent_name", valueColumnName = "ent_val", pkColumnValue = "articulo", initialValue = 0, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence_hibernate")
	private Long idArticulo;

	@Column(nullable = false)
	private String titulo;

	private String url;

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String contenido;

	@Column(nullable = false)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "USUARIO_ID_FK"), nullable = false)
	private Usuario usuario;
	
	@OneToMany(mappedBy="articuloId", cascade=CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<Comentario>();

	/**
	 * @return the idArticulo
	 */
	public Long getIdArticulo() {
		return idArticulo;
	}

	/**
	 * @param idArticulo
	 *            the idArticulo to set
	 */
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido
	 *            the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
