package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contratante;
import br.com.projeto.service.ContratanteService;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ContratanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contratante ob;

	@Inject
	private ContratanteService service;

	private List<Contratante> objs;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		ob = new Contratante();
		objs = service.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Contratante();
			carregar();
			Message.info("Contratante Salvo Com Sucesso!");
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

	public Contratante getContratante() {
		return ob;
	}

	public void setContratante(Contratante ob) {
		this.ob = ob;
	}

	public List<Contratante> getContratantes() {
		return objs;
	}

}
