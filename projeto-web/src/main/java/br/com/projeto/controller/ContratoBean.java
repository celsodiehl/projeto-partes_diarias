package br.com.projeto.controller;

import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.primefaces.model.DualListModel;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.model.Contratado;
import br.com.projeto.model.Contrato;
import br.com.projeto.model.ContratoVeiculo;
import br.com.projeto.model.Veiculo;
import br.com.projeto.service.ContratadoService;
import br.com.projeto.service.ContratoService;
import br.com.projeto.service.ContratoVeiculoService;
import br.com.projeto.service.VeiculoService;
import br.com.projeto.utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;

@Named
@ViewScoped
public class ContratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoService service;

	@Inject
	private ContratoVeiculoService contratoVeiculoService;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private ContratadoService contService;

	private Contrato contrato;

	private List<Contrato> objs;
	private List<Contratado> contratados;

	private List<Veiculo> veiculosDisponiveis;
	private List<Veiculo> veiculosSelecionados = new ArrayList<>();
	private DualListModel<Veiculo> dualVeiculos;

	// É CONSTRUIDO ASSIM QUE A CLASSE FOR CRIADA
	@PostConstruct
	public void carregar() {
		contrato = new Contrato();
		objs = service.todos();
		contratados = contService.todos();
		veiculosDisponiveis = veiculoService.todos();

		List<Veiculo> todosVeiculos = veiculoService.todos();

		dualVeiculos = new DualListModel<>(new ArrayList<>(todosVeiculos), // source
				new ArrayList<>() // target
		);
	}

	public void adicionar() {
		try {

			// limpa vínculos antigos
			contrato.getVeiculos().clear();

			// percorre veículos selecionados do picklist
			for (Veiculo v : dualVeiculos.getTarget()) {

				if (!v.getContratado().getId().equals(contrato.getContratado().getId())) {

					throw new NegocioException("Veículo não pertence ao contratado selecionado.");
				}

				ContratoVeiculo cv = new ContratoVeiculo();
				cv.setContrato(contrato);
				cv.setVeiculo(v);
				cv.setDataInicio(contrato.getDatainicio());
				cv.setDataFim(contrato.getDatafim());

				contrato.getVeiculos().add(cv);
			}

			// salva SOMENTE o contrato
			service.salvar(contrato);

			// limpa seleção
			dualVeiculos.setTarget(new ArrayList<>());

			contrato = new Contrato();
			carregar();

			Message.info("Contrato Salvo Com Sucesso!");

		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			service.remover(contrato);
			carregar();
			Message.info(contrato.getNome() + " Removido Com Sucesso!");
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	// MÉTODO PARA ORGANIZAR OS VEICULOS NO DIALOG
	public void prepararEdicao(Contrato contratoSelecionado) {

		this.contrato = contratoSelecionado;

		// Todos os veículos do sistema
		List<Veiculo> todosVeiculos = veiculoService.todos();

		// Veículos que já pertencem ao contrato
		List<Veiculo> veiculosContrato = contrato.getVeiculos().stream().map(cv -> cv.getVeiculo())
				.collect(Collectors.toList());
		// Remove da lista geral os que já estão no contrato
		todosVeiculos.removeAll(veiculosContrato);

		// source = esquerda
		// target = direita
		dualVeiculos = new DualListModel<>(todosVeiculos, veiculosContrato);
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getContratos() {
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

	public DualListModel<Veiculo> getDualVeiculos() {
		return dualVeiculos;
	}

	public void setDualVeiculos(DualListModel<Veiculo> dualVeiculos) {
		this.dualVeiculos = dualVeiculos;
	}

	// AO SELECIONAR CONTRATADO SÓ BUSCA VEICULOS DELE
	public void onContratadoChange() {

		if (contrato.getContratado() == null) {
			dualVeiculos = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
			return;
		}

		List<Veiculo> veiculos = veiculoService.buscarPorContratado(contrato.getContratado());

		dualVeiculos = new DualListModel<>(new ArrayList<>(veiculos), new ArrayList<>());
	}
	
	//Editar tinyMce
	public void editar(Contrato obj) {
	    //System.out.println("Chamou editar!");
	    this.contrato = obj;
	    
	    //System.out.println("Conteúdo: " + obj.getConteudo());
	}
	
	//Salvar tinyMce
	public void salvarContrato() throws NegocioException {

	    // Aqui o conteúdo do TinyMCE já está em:
	    // contrato.getConteudo()

	    if (contrato.getConteudo() == null || contrato.getConteudo().isBlank()) {
	        System.out.println("Conteúdo vazio!");
	    }

	    // Salva no banco
	    service.salvar(contrato);

	    System.out.println("Salvo com sucesso!");
	}

	/* Gerar/Imprimir Contrato	*/
	public String gerarHtmlFinal() {

	    String html = contrato.getConteudo();

	    if (html == null) html = "";
	    
	    html = html.replace("nome", contrato.getNome());
	    html = html.replace("contratado", contrato.getContratado().getNome());
	    
	    html = html.replace("valor", 
		        NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(contrato.getValor()));

	    html = html.replace("tipocontrato", contrato.getTipocontrato().getDescricao());

	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    html = html.replace("dataInicio", contrato.getDatainicio().format(fmt));
	    html = html.replace("dataFim", contrato.getDatafim().format(fmt));

	    String veiculos = contrato.getVeiculos().stream()
	            .map(v -> v.getVeiculo().getModelo())
	            .collect(Collectors.joining(", "));

	    html = html.replace("veiculos", veiculos);
	    
	    String placas = contrato.getVeiculos().stream()
	            .map(v -> v.getVeiculo().getPlaca())
	            .collect(Collectors.joining(", "));

	    html = html.replace("placas", placas);

	    // 🔥 Limpa e transforma em XHTML
	    Document doc = Jsoup.parse(html);
	    doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
	    doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
	    
	    // 🔥 GARANTE estrutura completa
	    return "<html>" +
	       "<head>" +
	       "<meta charset=\"UTF-8\" />" +
	       "<style>" +
           ".ql-align-center { text-align: center; }" +
           ".ql-align-right { text-align: right; }" +
           ".ql-align-justify { text-align: justify; }" +
	       "</style>" +
	       "</head>" +
	       "<body>" + doc.body().html() + "</body>" +
	       "</html>";
	    
	}
	
	private String substituir(String html, String variavel, String valor) {
	    return html.replaceAll("\\{\\{\\s*" + variavel + "\\s*\\}\\}", valor);
	}

	//Retorna no Browser
	public void gerarPdf() throws Exception {

	    String html = gerarHtmlFinal();

	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "inline; filename=contrato.pdf");

	    OutputStream os = response.getOutputStream();

	    PdfRendererBuilder builder = new PdfRendererBuilder();
	    builder.withHtmlContent(html, null);
	    builder.toStream(os);
	    builder.run();

	    os.close();
	    context.responseComplete();
	}

}
