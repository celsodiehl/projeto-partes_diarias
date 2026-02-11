package br.com.projeto.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "contratado") /*PRESTADOR DE SERVIÇOS*/
	public class Contratado implements Serializable, Base{
		
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nome;
		private String cnpj;
		
		//@OneToMany(mappedBy = "contratado")
	    //private List<Veiculo> veiculos;
		
		/* LISTA DE VEICULOS DO PRESTADOR/CONTRATADO */
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "contratado")
	    private List<Veiculo> veiculos;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public List<Veiculo> getVeiculos() {
			return veiculos;
		}
		public void setVeiculos(List<Veiculo> veiculos) {
			this.veiculos = veiculos;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Contratado other = (Contratado) obj;
			return Objects.equals(id, other.id);
		}
		
		

}
