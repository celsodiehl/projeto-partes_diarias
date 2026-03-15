package br.com.projeto.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.projeto.model.Base;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager = ConnectionFactory.getEntityManager();

	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	public void salvar(T t) {
		//COMENTEI CHAT
		//try {
			manager.getTransaction().begin();

			if (t.getId() == null) {
				manager.persist(t);
			} else {
				manager.merge(t);
			}
			manager.getTransaction().commit();

		//} catch (Exception e) {
			//manager.getTransaction().rollback();
		//}
	}

	public void remover(Class<T> clazz, Long id) {
		T t = buscarPorId(clazz, id);
		
		//COMENTEI CHAT
		//try {
			//manager.getTransaction().begin();
			manager.remove(t);
			//manager.getTransaction().commit();
		//} catch (Exception e) {
		//	manager.getTransaction().rollback();
		//}
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql) {
		return manager.createQuery(jpql).getResultList();
	}
	
	//BUSCA COM PARAMETROS, REUTULIZAVEL
	public List<T> buscarComParametros(Class<T> clazz, String jpql, Map<String, Object> parametros) {

	    TypedQuery<T> query = manager.createQuery(jpql, clazz);

	    for (Map.Entry<String, Object> entry : parametros.entrySet()) {
	        query.setParameter(entry.getKey(), entry.getValue());
	    }

	    return query.getResultList();
	}

}
