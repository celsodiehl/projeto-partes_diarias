package br.com.projeto.service;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.ContratoVeiculo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContratoVeiculoService implements Serializable {

	private static final long serialVersionUID = 1L;

	private DAO<ContratoVeiculo> dao = new DAO<>();

	public void salvar(ContratoVeiculo obj) throws NegocioException {

		if (obj.getVeiculo() == null) {
			throw new NegocioException("Veículo deve ser informado.");
		}

		dao.salvar(obj);
	}

	public void remover(ContratoVeiculo obj) throws NegocioException {
		dao.remover(ContratoVeiculo.class, obj.getId());
	}

	public List<ContratoVeiculo> buscarPorContrato(Long idContrato) {
		return dao.buscarTodos(
				"FROM ContratoVeiculo cv WHERE cv.contrato.id = " + idContrato + " ORDER BY cv.veiculo.modelo");
	}
}