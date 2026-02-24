package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Motorista;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class MotoristaService implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/* ALTEREI AKI CHAT
	@PersistenceContext
	private EntityManager em;

	public Motorista find(Long id) {
		return em.find(Motorista.class, id);
	} */
	
	
	//@Inject
	//private DAO<Motorista> dao;
	private DAO<Motorista> dao = new DAO<>();
	

	public void salvar(Motorista obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Nome Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Motorista obj) throws NegocioException {
		dao.remover(Motorista.class, obj.getId());
	}
	
	public List<Motorista> todos() {
		return dao.buscarTodos("FROM Motorista o ORDER BY o.nome");
	}
	
	public Motorista buscarPorId(Long id) {
	    return dao.buscarPorId(Motorista.class, id);
	}
	
}
