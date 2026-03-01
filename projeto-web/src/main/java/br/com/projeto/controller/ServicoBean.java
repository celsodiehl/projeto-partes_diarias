package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Servico;
import br.com.projeto.service.ServicoService;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Servico ob;

	@Inject
	private ServicoService service;

	private List<Servico> objs;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		ob = new Servico();
		objs = service.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Servico();
			carregar();
			Message.info("Serviço Salvo Com Sucesso!");
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

	public Servico getServico() {
		return ob;
	}

	public void setServico(Servico ob) {
		this.ob = ob;
	}

	public List<Servico> getServicos() {
		return objs;
	}
	

}
