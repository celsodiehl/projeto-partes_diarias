package br.com.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contratado;
import br.com.projeto.service.ContratadoService;
import br.com.projeto.util.UtilRelatorios;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ContratadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contratado ob;

	@Inject
	private ContratadoService service;

	private List<Contratado> objs;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		ob = new Contratado();
		objs = service.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Contratado();
			carregar();
			Message.info("Veiculo Salvo Com Sucesso!");
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

	public void imprimeContratados() {
		HashMap parametros = new HashMap();
		UtilRelatorios.imprimeRelatorio("RelContratado", parametros, service.todos());
	}
	
	public void imprimeContratado() {
		try {
			//ob = service.buscarPorId(id);
			List<Contratado> lista = new ArrayList<>();
			lista.add(ob);
			HashMap parametros = new HashMap();
			UtilRelatorios.imprimeRelatorio("RelContratado", parametros, lista);
		} catch (Exception e) {
			Message.info("Erro ao Imprimir: "  + e.getMessage());
		}
	}

	public Contratado getContratado() {
		return ob;
	}

	public void setContratado(Contratado ob) {
		this.ob = ob;
	}

	public List<Contratado> getContratados() {
		return objs;
	}

}
