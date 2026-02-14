package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Marca;
import br.com.projeto.service.MarcaService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Marca ob;

	@Inject
	private MarcaService service;

	private List<Marca> objs;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		// medicamento = new Medicamento(); // 👈 OBRIGATÓRIO
		// medicamentos = new ArrayList<>();
		objs = service.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Marca();
			carregar();
			Message.info("Marca Salvo Com Sucesso!");
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

	public Marca getMarca() {
		return ob;
	}

	public void setMarca(Marca marca) {
		this.ob = marca;
	}

	public List<Marca> getMarcas() {
		return objs;
	}
	

}
