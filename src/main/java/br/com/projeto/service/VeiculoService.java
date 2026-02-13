package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.model.Veiculo;
import br.com.projeto.utility.NegocioException;
import jakarta.inject.Inject;

public class VeiculoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<Veiculo> dao;
	

	public void salvar(Veiculo obj) throws NegocioException {
		if (obj.getModelo().length() < 3) {
			throw new NegocioException("Modelo Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Veiculo obj) throws NegocioException {
		dao.remover(Veiculo.class, obj.getId());
	}
	
	public List<Veiculo> todos() {
		return dao.buscarTodos("FROM Veiculo o ORDER BY o.modelo");
	}
	
	public Veiculo buscarPorId(Long id) {
	    return dao.buscarPorId(Veiculo.class, id);
	}
	
}
