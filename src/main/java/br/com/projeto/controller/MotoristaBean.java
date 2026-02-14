package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Motorista;
import br.com.projeto.service.MotoristaService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class MotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Motorista ob;

	@Inject
	private MotoristaService service;

	private List<Motorista> objs;

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
			ob = new Motorista();
			carregar();
			Message.info("Motorista Salvo Com Sucesso!");
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

	public Motorista getMotorista() {
		return ob;
	}

	public void setMotorista(Motorista ob) {
		this.ob = ob;
	}

	public List<Motorista> getMotoristas() {
		return objs;
	}

}
