package br.com.projeto.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.projeto.dao.DAO;
import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.ParteDiaria;
import br.com.projeto.model.Veiculo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParteDiariaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
	//SERVICE RELATORIO
	public List<ParteDiaria> porPeriodo(Date ini, Date fim, Veiculo veiculo) {

	    String jpql = "FROM ParteDiaria p WHERE p.data BETWEEN :ini AND :fim AND p.veiculo = :veiculo "
	                + "ORDER BY p.data";

	    Map<String, Object> params = new HashMap<>();
	    params.put("ini", ini);
	    params.put("fim", fim);
	    params.put("veiculo", veiculo);

	    return dao.buscarComParametros(ParteDiaria.class, jpql, params);
	}
    

}
