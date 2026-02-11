package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Contratado;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class ContratadoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Contratado> dao;
	

	public void salvar(Contratado obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Contratado obj) throws NegocioException {
		dao.remover(Contratado.class, obj.getId());
	}
	
	public List<Contratado> todos() {
		return dao.buscarTodos("FROM Contratado o ORDER BY o.nome");
	}
	
	public Contratado buscarPorId(Long id) {
	    return dao.buscarPorId(Contratado.class, id);
	}
}
