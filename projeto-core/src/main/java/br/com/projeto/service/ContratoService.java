package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contrato;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class ContratoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* ALTEREI AKI CHAT
	@PersistenceContext
	private EntityManager em;

	public Contrato find(Long id) {
		return em.find(Contrato.class, id);
	} */
	
	
	//@Inject
	//private DAO<Contrato> dao;
	private DAO<Contrato> dao = new DAO<>();
	

	public void salvar(Contrato obj) throws NegocioException {
		if (obj.getNome().length() < 3) {
			throw new NegocioException("Contrato Não Pode Ser Menor que 3 Caracteres!");
		}
		dao.salvar(obj);
	}
	
	public void remover(Contrato obj) throws NegocioException {
		dao.remover(Contrato.class, obj.getId());
	}
	
	public List<Contrato> todos() {
		return dao.buscarTodos("FROM Contrato o ORDER BY o.nome");
	}
	
	public Contrato buscarPorId(Long id) {
	    return dao.buscarPorId(Contrato.class, id);
	}
	
}
