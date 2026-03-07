package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Empresa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaService implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private DAO<Empresa> dao = new DAO<>();

	public void salvar(Empresa obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}

	public void remover(Empresa obj) throws NegocioException {
		dao.remover(Empresa.class, obj.getId());
	}

	public List<Empresa> todos() {
		return dao.buscarTodos("FROM Empresa o ORDER BY o.nome");
	}

	public Empresa buscarPorId(Long id) {
		return dao.buscarPorId(Empresa.class, id);
	}

}
