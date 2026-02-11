package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Marca;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class MarcaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Marca> dao;
	

	public void salvar(Marca obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Marca obj) throws NegocioException {
		dao.remover(Marca.class, obj.getId());
	}
	
	public List<Marca> todos() {
		return dao.buscarTodos("FROM Marca o ORDER BY o.nome");
	}
	
	public Marca buscarPorId(Long id) {
	    return dao.buscarPorId(Marca.class, id);
	}
	
}
