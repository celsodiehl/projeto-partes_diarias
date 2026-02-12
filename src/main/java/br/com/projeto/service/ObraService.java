package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Obra;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class ObraService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Obra> dao;
	

	public void salvar(Obra obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Obra Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Obra obj) throws NegocioException {
		dao.remover(Obra.class, obj.getId());
	}
	
	public List<Obra> todos() {
		return dao.buscarTodos("FROM Obra o ORDER BY o.nome");
	}
	
	public Obra buscarPorId(Long id) {
	    return dao.buscarPorId(Obra.class, id);
	}
	
}
