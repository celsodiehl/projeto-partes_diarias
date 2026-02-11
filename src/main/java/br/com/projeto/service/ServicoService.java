package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Servico;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class ServicoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Servico> dao;
	

	public void salvar(Servico obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Servico obj) throws NegocioException {
		dao.remover(Servico.class, obj.getId());
	}
	
	public List<Servico> todos() {
		return dao.buscarTodos("FROM Servico o ORDER BY o.nome");
	}
	
	public Servico buscarPorId(Long id) {
	    return dao.buscarPorId(Servico.class, id);
	}
	
}
