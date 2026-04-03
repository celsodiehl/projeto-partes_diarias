package br.com.projeto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "contrato")
	public class Contrato implements Serializable, Base{
		
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
	    @ManyToOne(optional = false)
	    @JoinColumn(name = "contratado_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contratado"))
	    private Contratado contratado;
	    
	    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<ContratoVeiculo> veiculos = new ArrayList<>();
	    
		private String nome;
		private LocalDate datainicio;
		private LocalDate datafim;
		private boolean ativo;
		private TipoContrato tipocontrato;
		private BigDecimal valor;
		
		//Gerar Contrato no TinyMce
		@Lob
		@Column(columnDefinition = "TEXT")
		private String conteudo;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public Contratado getContratado() {
			return contratado;
		}
		public void setContratado(Contratado contratado) {
			this.contratado = contratado;
		}
		public List<ContratoVeiculo> getVeiculos() {
			return veiculos;
		}
		public void setVeiculos(List<ContratoVeiculo> veiculos) {
			this.veiculos = veiculos;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public LocalDate getDatainicio() {
			return datainicio;
		}
		public void setDatainicio(LocalDate datainicio) {
			this.datainicio = datainicio;
		}
		public LocalDate getDatafim() {
			return datafim;
		}
		public void setDatafim(LocalDate datafim) {
			this.datafim = datafim;
		}
		public boolean isAtivo() {
			return ativo;
		}
		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}
		public TipoContrato getTipocontrato() {
			return tipocontrato;
		}
		public void setTipocontrato(TipoContrato tipocontrato) {
			this.tipocontrato = tipocontrato;
		}
		public BigDecimal getValor() {
			return valor;
		}
		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}
		
		public String getConteudo() {
			//Proteger se estiver vazio
			return conteudo != null ? conteudo : "";
		}
		public void setConteudo(String conteudo) {
			this.conteudo = conteudo;
		}
		
		public void adicionarVeiculo(Veiculo veiculo) {

		    if (!veiculo.getContratado().getId()
		            .equals(this.contratado.getId())) {

		        throw new IllegalStateException(
		            "Veículo não pertence ao contratado deste contrato."
		        );
		    }

		    ContratoVeiculo cv = new ContratoVeiculo();
		    cv.setContrato(this);
		    cv.setVeiculo(veiculo);

		    this.veiculos.add(cv);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Contrato other = (Contrato) obj;
			return Objects.equals(id, other.id);
		}
		
		

}
