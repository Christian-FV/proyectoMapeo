package mx.edu.uacm.blog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity(name="comentario")
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sequence_hibernate", table = "sequence_hibernate", pkColumnName = "ent_name", valueColumnName = "ent_val", pkColumnValue = "comentario", initialValue = 0, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence_hibernate")
	private Long idComentario;

	@Column(nullable = false)
	private String contenido;

	@Column(nullable = false)
	private Date fechaCom;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "articuloId", foreignKey = @ForeignKey(name = "ARTICULO_IDC_FK"), nullable = false)
	private Articulo articuloId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuarioId", foreignKey = @ForeignKey(name = "USUARIO_IDC_FK"), nullable = false)
	private Usuario usuarioId;

	/**
	 * @return the idComentario
	 */
	public Long getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario
	 *            the idComentario to set
	 */
	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
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
	 * @return the fechaCom
	 */
	public Date getFechaCom() {
		return fechaCom;
	}

	/**
	 * @param fechaCom
	 *            the fechaCom to set
	 */
	public void setFechaCom(Date fechaCom) {
		this.fechaCom = fechaCom;
	}

	/**
	 * @return the articuloId
	 */
	public Articulo getArticuloId() {
		return articuloId;
	}

	/**
	 * @param articuloId the articuloId to set
	 */
	public void setArticuloId(Articulo articuloId) {
		this.articuloId = articuloId;
	}

	/**
	 * @return the usuarioId
	 */
	public Usuario getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
