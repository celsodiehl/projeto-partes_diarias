package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Contratante;
import br.com.projeto.model.Obra;
import br.com.projeto.service.ContratanteService;
import br.com.projeto.service.ObraService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ObraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ObraService service;

	@Inject
	private ContratanteService contService;

	private Obra ob;

	private List<Obra> objs;
	private List<Contratante> contratantes;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		ob = new Obra();
		objs = service.todos();
		contratantes = contService.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Obra();
			carregar();
			Message.info("Obra Salvo Com Sucesso!");
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

	public Obra getObra() {
		return ob;
	}

	public void setObra(Obra ob) {
		this.ob = ob;
	}

	public List<Obra> getObras() {
		return objs;
	}

	public List<Contratante> getContratantes()  throws NegocioException {
		try {
			if (contratantes == null || (contratantes.size() == 0)) {
				contratantes = contService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return contratantes;
	}

	public void setContratantes(List<Contratante> contratantes) {
		this.contratantes = contratantes;
	}
	


}
