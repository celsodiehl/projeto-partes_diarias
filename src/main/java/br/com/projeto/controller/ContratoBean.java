package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Contratado;
import br.com.projeto.model.Contrato;
import br.com.projeto.service.ContratadoService;
import br.com.projeto.service.ContratoService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
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
	private ContratadoService contService;

	private Contrato ob;

	private List<Contrato> objs;
	private List<Contratado> contratados;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		ob = new Contrato();
		objs = service.todos();
		contratados = contService.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Contrato();
			carregar();
			Message.info("Contrato Salvo Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(ob);
			carregar();
			Message.info(ob.getNome() + " Removido Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public Contrato getContrato() {
		return ob;
	}

	public void setContrato(Contrato ob) {
		this.ob = ob;
	}

	public List<Contrato> getContratos() {
		return objs;
	}
	

	public List<Contratado> getContratados()  throws NegocioException {
		try {
			if (contratados == null || (contratados.size() == 0)) {
				contratados = contService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return contratados;
	}

	public void setContrados(List<Contratado> contratados) {
		this.contratados = contratados;
	}
	


}
