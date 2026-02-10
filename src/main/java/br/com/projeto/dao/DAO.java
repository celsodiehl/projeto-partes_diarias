package br.com.projeto.dao;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Base;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class DAO<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static EntityManager manager = ConnectionFactory.getEntityManager();

	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	public void salvar(T t) {
		try {
			manager.getTransaction().begin();

			if (t.getId() == null) {
				manager.persist(t);
			} else {
				manager.merge(t);
			}
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remover(Class<T> clazz, Long id) {
		T t = buscarPorId(clazz, id);

		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql) {
		Query qr = manager.createQuery(jpql);
		return qr.getResultList();
	}

}
