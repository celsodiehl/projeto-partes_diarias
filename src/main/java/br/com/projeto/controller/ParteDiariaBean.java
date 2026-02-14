package br.com.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Contrato;
import br.com.projeto.model.Obra;
import br.com.projeto.model.ParteDiaria;
import br.com.projeto.model.Servico;
import br.com.projeto.model.Veiculo;
import br.com.projeto.service.ContratoService;
import br.com.projeto.service.ObraService;
import br.com.projeto.service.ParteDiariaService;
import br.com.projeto.service.ServicoService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ParteDiariaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParteDiariaService service;

	@Inject
	private ObraService obraService;

	@Inject
	private ContratoService contService;

	@Inject
	private ServicoService servService;

	// @Inject
	private ParteDiaria ptdia;

	private List<ParteDiaria> ptdias;
	private List<Obra> obras;
	private List<Contrato> contratos;
	private List<Veiculo> veiculos;
	private List<Servico> servicos;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		// medicamentos = new ArrayList<>();

		ptdia = new ParteDiaria();
		ptdias = service.todos();

		contratos = contService.todos(); // ✅ AQUI
		veiculos = new ArrayList<>();
		obras = obraService.todos();
		servicos = servService.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ptdia);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ptdia = new ParteDiaria();
			carregar();
			Message.info("ParteDiaria Salvo Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(ptdia);
			carregar();
			Message.info(ptdia.getObra().getNome() + " Removido Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void carregarVeiculos() {
		if (ptdia.getContrato().getContratado() != null) {
			veiculos = ptdia.getContrato().getContratado().getVeiculos();
		} else {
			veiculos = new ArrayList<>();
		}
		ptdia.setVeiculo(null); // limpa seleção anterior
	}

	public ParteDiaria getPtdia() {
		return ptdia;
	}

	public void setPtdia(ParteDiaria ptdia) {
		this.ptdia = ptdia;
	}

	public List<ParteDiaria> getPtdias() {
		return ptdias;
	}

	public List<Obra> getObras() {
		try {
			if (obras == null || (obras.size() == 0)) {
				obras = obraService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public List<Contrato> getContratos() {
		try {
			if (contratos == null || (contratos.size() == 0)) {
				contratos = contService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<Servico> getServicos() {
		try {
			if (servicos == null || (servicos.size() == 0)) {
				servicos = servService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	
	

}
