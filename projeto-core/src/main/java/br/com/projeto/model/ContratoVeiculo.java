package br.com.projeto.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/* Saber histórico completo
Permitir mesmo veículo em contratos diferentes
Controlar período
Evitar conflito de veículo duplicado no mesmo período */
@Entity
@Table(name = "contrato_veiculo")
public class ContratoVeiculo implements Serializable, Base {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contrato"))
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_veiculo"))
    private Veiculo veiculo;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
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
		ContratoVeiculo other = (ContratoVeiculo) obj;
		return Objects.equals(id, other.id);
	}
	
	
    
    
}
