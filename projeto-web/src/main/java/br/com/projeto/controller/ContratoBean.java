package br.com.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contratado;
import br.com.projeto.model.Contrato;
import br.com.projeto.model.ContratoVeiculo;
import br.com.projeto.model.Veiculo;
import br.com.projeto.service.ContratadoService;
import br.com.projeto.service.ContratoService;
import br.com.projeto.service.ContratoVeiculoService;
import br.com.projeto.service.VeiculoService;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ContratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoService service;

	@Inject
	private ContratoVeiculoService contratoVeiculoService;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private ContratadoService contService;

	private Contrato contrato;

	private List<Contrato> objs;
	private List<Contratado> contratados;

	private List<Veiculo> veiculosDisponiveis;
	private List<Veiculo> veiculosSelecionados = new ArrayList<>();
	private DualListModel<Veiculo> dualVeiculos;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		contrato = new Contrato();
		objs = service.todos();
		contratados = contService.todos();
		veiculosDisponiveis = veiculoService.todos();

		List<Veiculo> todosVeiculos = veiculoService.todos();

		dualVeiculos = new DualListModel<>(new ArrayList<>(todosVeiculos), // source
				new ArrayList<>() // target
		);
	}

	public void adicionar() {
		try {

			// limpa vínculos antigos
			contrato.getVeiculos().clear();

			// percorre veículos selecionados do picklist
			for (Veiculo v : dualVeiculos.getTarget()) {

				ContratoVeiculo cv = new ContratoVeiculo();
				cv.setContrato(contrato);
				cv.setVeiculo(v);
				cv.setDataInicio(contrato.getDatainicio());
				cv.setDataFim(contrato.getDatafim());

				contrato.getVeiculos().add(cv);
			}

			// salva SOMENTE o contrato
			service.salvar(contrato);

			// limpa seleção
			dualVeiculos.setTarget(new ArrayList<>());

			contrato = new Contrato();
			carregar();

			Message.info("Contrato Salvo Com Sucesso!");

		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(contrato);
			carregar();
			Message.info(contrato.getNome() + " Removido Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getContratos() {
		return objs;
	}

	public List<Contratado> getContratados() throws NegocioException {
		try {
			if (contratados == null || (contratados.size() == 0)) {
				contratados = contService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return contratados;
	}

	public void setContratados(List<Contratado> contratados) {
		this.contratados = contratados;
	}

	public DualListModel<Veiculo> getDualVeiculos() {
		return dualVeiculos;
	}

	public void setDualVeiculos(DualListModel<Veiculo> dualVeiculos) {
		this.dualVeiculos = dualVeiculos;
	}

}
