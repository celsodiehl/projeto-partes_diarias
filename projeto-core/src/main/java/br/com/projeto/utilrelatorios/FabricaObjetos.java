package br.com.projeto.utilrelatorios;

import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Contratado;
import br.com.projeto.model.Motorista;
import br.com.projeto.model.Veiculo;

public class FabricaObjetos {

	public static List<Contratado> carregar(){
		List<Contratado> lista = new ArrayList<Contratado>();
		
		Contratado c = new Contratado();
		c.setId(1L);
		c.setNome("SOLLUS");
		c.setCnpj("789.345.0001-45");
		
		Motorista m = new Motorista();
		m.setNome("CLEITON");
		m.setCategoria("E");
		
		Veiculo v = new Veiculo();
	    v.setModelo("F-100");
		v.setPlaca("KBH-1234");
		v.setMotorista(m);
		v.setContratado(c);
		
		c.addVeiculo(v);
		
		lista.add(c);
		return lista;
		
	}
}
