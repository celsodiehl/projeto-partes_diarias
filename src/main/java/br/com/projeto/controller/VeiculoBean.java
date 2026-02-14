package br.com.projeto.controller;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.model.Contratado;
import br.com.projeto.model.Marca;
import br.com.projeto.model.Motorista;
import br.com.projeto.model.Veiculo;
import br.com.projeto.service.ContratadoService;
import br.com.projeto.service.MarcaService;
import br.com.projeto.service.MotoristaService;
import br.com.projeto.service.VeiculoService;
import br.com.projeto.utility.Message;
import br.com.projeto.utility.NegocioException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class VeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService service;

	@Inject
	private MarcaService marService;

	@Inject
	private ContratadoService contService;

	@Inject
	private MotoristaService motService;

	// @Inject
	private Veiculo ob;

	private List<Veiculo> objs;
	private List<Marca> marcas;
	private List<Contratado> contratados;
	private List<Motorista> motoristas;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		// medicamento = new Medicamento(); // 👈 OBRIGATÓRIO
		// medicamentos = new ArrayList<>();

		ob = new Veiculo();
		objs = service.todos();

		contratados = contService.todos(); // ✅ AQUI
		motoristas = motService.todos();
		marcas = marService.todos();
	}

	public void adicionar() {
		try {
			service.salvar(ob);
			// QUANDO SALVAR LIMPA O FORMULÁRIO
			ob = new Veiculo();
			carregar();
			Message.info("Veiculo Salvo Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(ob);
			carregar();
			Message.info(ob.getModelo() + " Removido Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public Veiculo getVeiculo() {
		return ob;
	}

	public void setVeiculo(Veiculo ob) {
		this.ob = ob;
	}

	public List<Veiculo> getVeiculos() {
		return objs;
	}

	public List<Contratado> getContratados() throws NegocioException {

		try {
			if (contratados == null || (contratados.size() == 0)) {
				contratados = contService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return contratados;
	}

	public void setContratados(List<Contratado> contratados) {
		this.contratados = contratados;
	}

	public List<Motorista> getMotoristas() {
		try {
			if (motoristas == null || (motoristas.size() == 0)) {
				motoristas = motService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	public List<Marca> getMarcas() {
		try {
			if (marcas == null || (marcas.size() == 0)) {
				marcas = marService.todos();
			}
		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

}
