package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.ParteDiaria;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class ParteDiariaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<ParteDiaria> dao;

	public void salvar(ParteDiaria obj) throws NegocioException {
		if (obj.getObra() == null) {
			throw new NegocioException("Selecione uma obra!");
		}
		if (obj.getObra().getNome().length() < 3) {
			throw new NegocioException("Nome da Obra Inválido!");
		}
		dao.salvar(obj);
	}

	public void remover(ParteDiaria obj) throws NegocioException {
		dao.remover(ParteDiaria.class, obj.getId());
	}

	public List<ParteDiaria> todos() {
		return dao.buscarTodos("FROM ParteDiaria");
	}

}
