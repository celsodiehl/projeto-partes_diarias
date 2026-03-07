package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Empresa;
import br.com.projeto.service.EmpresaService;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {

		private static final long serialVersionUID = 1L;

		private Empresa empresa;

		@Inject
		private EmpresaService service;

		private List<Empresa> empresas;

		// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
		@PostConstruct
		public void carregar() {
			empresa = new Empresa();
			empresas = service.todos();
		}

		public void adicionar() {
			try {
				service.salvar(empresa);
				// QUANDO SALVAR LIMPA O FORMULÁRIO
				empresa = new Empresa();
				carregar();
				Message.info("Empresa Salvo Com Sucesso!");
			} catch (NegocioException e) {
				Message.erro(e.getMessage());
			}
		}

		public void excluir() {
			try {
				service.remover(empresa);
				carregar();
				Message.info(empresa.getNome() + " Removido Com Sucesso!");
			} catch (NegocioException e) {
				Message.erro(e.getMessage());
			}
		}

		public Empresa getEmpresa() {
			return empresa;
		}

		public void setEmpresa(Empresa empresa) {
			this.empresa = empresa;
		}

		public List<Empresa> getEmpresas() {
			return empresas;
		}


		

}
