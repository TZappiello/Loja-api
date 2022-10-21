//package com.zap.lojazap.model.entity;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
//
//import com.zap.lojazap.model.enums.StatusLancamento;
//import com.zap.lojazap.model.enums.TipoLancamento;
//
//@Entity
//@Table(name = "lancamento", schema = "financas")
//public class Lancamento {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Long id;
//
//	@Column(name = "descricao")
//	private String descricao;
//
//	@Column(name = "mes")
//	private Integer mes;
//
//	@Column(name = "ano")
//	private Integer ano;
//
//	@ManyToOne
//	@JoinColumn(name = "id_usuario")
//	private Usuario usuario;
//
//	@Column(name = "valor")
//	private BigDecimal valor;
//
//	@Column(name = "data_cadastro")
//	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
//	private LocalDate dataCadastro;
//
//	@Column(name = "tipo")
//	@Enumerated(value = EnumType.STRING)
//	private TipoLancamento tipo;
//
//	@Column(name = "status")
//	@Enumerated(value = EnumType.STRING)
//	private StatusLancamento status;
//
//	public Lancamento() {
//
//	}
//
//	public Lancamento(Long id, String descricao, Integer mes, Integer ano, Usuario usuario, BigDecimal valor,
//			LocalDate dataCadastro, TipoLancamento tipo, StatusLancamento status) {
//		super();
//		this.id = id;
//		this.descricao = descricao;
//		this.mes = mes;
//		this.ano = ano;
//		this.usuario = usuario;
//		this.valor = valor;
//		this.dataCadastro = dataCadastro;
//		this.tipo = tipo;
//		this.status = status;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public Integer getMes() {
//		return mes;
//	}
//
//	public void setMes(Integer mes) {
//		this.mes = mes;
//	}
//
//	public Integer getAno() {
//		return ano;
//	}
//
//	public void setAno(Integer ano) {
//		this.ano = ano;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	public BigDecimal getValor() {
//		return valor;
//	}
//
//	public void setValor(BigDecimal valor) {
//		this.valor = valor;
//	}
//
//	public LocalDate getDataCadastro() {
//		return dataCadastro;
//	}
//
//	public void setDataCadastro(LocalDate dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//
//	public TipoLancamento getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoLancamento tipo) {
//		this.tipo = tipo;
//	}
//
//	public StatusLancamento getStatus() {
//		return status;
//	}
//
//	public void setStatus(StatusLancamento status) {
//		this.status = status;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
//		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
//		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
//		result = prime * result + ((status == null) ? 0 : status.hashCode());
//		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
//		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
//		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Lancamento other = (Lancamento) obj;
//		if (ano == null) {
//			if (other.ano != null)
//				return false;
//		} else if (!ano.equals(other.ano))
//			return false;
//		if (dataCadastro == null) {
//			if (other.dataCadastro != null)
//				return false;
//		} else if (!dataCadastro.equals(other.dataCadastro))
//			return false;
//		if (descricao == null) {
//			if (other.descricao != null)
//				return false;
//		} else if (!descricao.equals(other.descricao))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (mes == null) {
//			if (other.mes != null)
//				return false;
//		} else if (!mes.equals(other.mes))
//			return false;
//		if (status != other.status)
//			return false;
//		if (tipo != other.tipo)
//			return false;
//		if (usuario == null) {
//			if (other.usuario != null)
//				return false;
//		} else if (!usuario.equals(other.usuario))
//			return false;
//		if (valor == null) {
//			if (other.valor != null)
//				return false;
//		} else if (!valor.equals(other.valor))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Lancamento [id=" + id + ", descricao=" + descricao + ", mes=" + mes + ", ano=" + ano + ", usuario="
//				+ usuario + ", valor=" + valor + ", dataCadastro=" + dataCadastro + ", tipo=" + tipo + ", status="
//				+ status + "]";
//	}
//
//}
