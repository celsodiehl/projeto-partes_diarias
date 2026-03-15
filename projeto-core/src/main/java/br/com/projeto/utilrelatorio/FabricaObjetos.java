package br.com.projeto.utilrelatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Contratado;
import br.com.projeto.model.Motorista;
import br.com.projeto.model.Veiculo;

public class FabricaObjetos {

	public static List<Contratado> carregar(){
		List<Contratado> lista = new ArrayList<>();
		
		Contratado c = new Contratado();
		c.setId(1L);
		c.setNome("SOLLUS");
		c.setCnpj("789.345.0001-45");
		
		Veiculo v = new Veiculo();
	    v.setModelo("F-100");
		v.setPlaca("KBH-1234");
		v.setContratado(c);
		c.addVeiculo(v);
		
		Motorista m = new Motorista();
		m.setId(2L);
		m.setNome("JOÃO DO TAXI");
		m.setCategoria("A D");
		v.setMotorista(m);
		c.getVeiculos().add(v);
		
		//lista.add(c);
		return lista;
		
	}
}
