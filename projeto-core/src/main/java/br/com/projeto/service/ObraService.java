package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Obra;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class ObraService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* ALTEREI AKI CHAT
	@PersistenceContext
	private EntityManager em;

	public Obra find(Long id) {
		return em.find(Obra.class, id);
	} */
	
	
	//@Inject CHAT
	//private DAO<Obra> dao;
	
	//CHAT
	private DAO<Obra> dao = new DAO<>();
	

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
