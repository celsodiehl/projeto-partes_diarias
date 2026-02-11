package br.com.projeto.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "contratante") /*QUEM CONTRATA/PAGA PELOS SERVIÇOS*/
	public class Contratante implements Serializable, Base{
		
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nome;
		private String cnpj;
		//private LocalDate dataNascimento;
		private String cidade;
		private String contato;
		private String telefone;
		
		
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
		
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		public String getContato() {
			return contato;
		}
		public void setContato(String contato) {
			this.contato = contato;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
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
			Contratante other = (Contratante) obj;
			return Objects.equals(id, other.id);
		}
		
		

}
