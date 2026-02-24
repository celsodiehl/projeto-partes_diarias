package br.com.projeto.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parte_diaria")
public class ParteDiaria implements Serializable, Base{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "obra_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_obra"))
    private Obra obra;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "contrato_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contrato"))
    private Contrato contrato;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_veiculo"))
    private Veiculo veiculo;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "servico_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_servico"))
    private Servico servico;

    private LocalDate data;
	private LocalTime horaInicio;
    private LocalTime horaFim;
    private String odomInicio;
    private String odomFim;
    private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
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
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getOdomInicio() {
		return odomInicio;
	}

	public void setOdomInicio(String odomInicio) {
		this.odomInicio = odomInicio;
	}

	public String getOdomFim() {
		return odomFim;
	}

	public void setOdomFim(String odomFim) {
		this.odomFim = odomFim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
    public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

    
    
}
