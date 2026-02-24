package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contratado;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class ContratadoService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/* ALTEREI AKI CHAT
	@PersistenceContext
	private EntityManager em;

	public Contratado find(Long id) {
		return em.find(Contratado.class, id);
	} */
	
	
	//@Inject
	//private DAO<Contratado> dao;
	//CHAT
	private DAO<Contratado> dao = new DAO<>();
	
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
