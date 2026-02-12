package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Contratante;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class ContratanteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Contratante> dao;
	

	public void salvar(Contratante obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Contratante obj) throws NegocioException {
		dao.remover(Contratante.class, obj.getId());
	}
	
	public List<Contratante> todos() {
		return dao.buscarTodos("FROM Contratante o ORDER BY o.nome");
	}
	
	public Contratante buscarPorId(Long id) {
	    return dao.buscarPorId(Contratante.class, id);
	}
}
