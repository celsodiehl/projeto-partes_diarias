package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.ParteDiaria;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class ParteDiariaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* ALTEREI AKI CHAT
	@PersistenceContext
	private EntityManager em;

	public ParteDiaria find(Long id) {
		return em.find(ParteDiaria.class, id);
	} */

	//@Inject
	//private DAO<ParteDiaria> dao;
	private DAO<ParteDiaria> dao = new DAO<>();

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
