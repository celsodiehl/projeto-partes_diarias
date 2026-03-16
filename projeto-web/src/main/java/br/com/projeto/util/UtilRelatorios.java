package br.com.projeto.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import br.com.projeto.utility.Message;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtilRelatorios {
	
	public static void imprimeRelatorio( String relatorioNome, HashMap parametros, List lista) {
		
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			FacesContext facContext = FacesContext.getCurrentInstance();
			facContext.responseComplete();
			ServletContext sContext = (ServletContext) facContext.getExternalContext().getContext();
			
			String path = sContext.getRealPath("/WEB-INF/relatorio/");
			parametros.put("SUBREPORT_DIR", path + File.separator);
			
			
			String relatorio = path + relatorioNome + ".jasper";

			JasperPrint jasPrint = JasperFillManager.fillReport(relatorio, parametros, dataSource);

	       // InputStream relatorioStream = sContext.getResourceAsStream(path + relatorioNome + ".jasper");
	        
	        File file = new File(relatorio);
	        if (!file.exists()) {
	            throw new RuntimeException("Relatório não encontrado: " + relatorio);
	        }
	            
			//Array de byte
			byte[] b = JasperExportManager.exportReportToPdf(jasPrint);
			HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			res.setContentType("application/pdf");
			
			int codigo = (int) (Math.random() * 1000);
			
			//Gera relatório e disponibiliza diretamente na página
			res.setHeader("Content-disposition", "inline; filename=relatorio_" + codigo + ".pdf");
			res.getOutputStream().write(b);
			res.getCharacterEncoding();
			FacesContext.getCurrentInstance().responseComplete();
			
		}catch (Exception e) {
			Message.erroRelatorio(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
