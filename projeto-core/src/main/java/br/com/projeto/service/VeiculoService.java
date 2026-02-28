package br.com.projeto.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // ALTEREI CHAT
public class VeiculoService implements Serializable {

	private static final long serialVersionUID = 1L;

	private DAO<Veiculo> dao = new DAO<>();

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

	// Quando selecionar um Contrato, carregar os Veículos do Contratado daquele contrato.
	public List<Veiculo> buscarPorContrato(Long idContrato) {

	    String jpql = "SELECT v FROM Veiculo v " +
	                  "JOIN v.contratos cv " +
	                  "WHERE cv.contrato.id = :idContrato " +
	                  "ORDER BY v.modelo";

	    Map<String, Object> params = new HashMap<>();
	    params.put("idContrato", idContrato);

	    return dao.buscarComParametros(Veiculo.class, jpql, params);
	}
	
	public List<Veiculo> buscarVeiculosAtivosPorContrato(Long idContrato) {

	    String jpql = "SELECT cv.veiculo FROM ContratoVeiculo cv " +
	                  "WHERE cv.contrato.id = :idContrato " +
	                  "AND cv.ativo = true " +
	                  "ORDER BY cv.veiculo.modelo";

	    Map<String, Object> params = new HashMap<>();
	    params.put("idContrato", idContrato);

	    return dao.buscarComParametros(Veiculo.class, jpql, params);
	}

}
