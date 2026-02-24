package br.com.projeto.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "veiculo")
	public class Veiculo implements Serializable, Base{
		
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
	    @ManyToOne(optional = false)
	    @JoinColumn(name = "contratado_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contratado"))
	    private Contratado contratado;
	    
	    @ManyToOne(optional = false)
	    @JoinColumn(name = "motorista_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_motorista"))
	    private Motorista motorista;
		
	    @ManyToOne(optional = false)
	    @JoinColumn(name = "marca_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_marca"))
	    private Marca marca;
	    
		private String modelo;
		private String placa;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		
		public Marca getMarca() {
			return marca;
		}
		public void setMarca(Marca marca) {
			this.marca = marca;
		}
		public String getPlaca() {
			return placa;
		}
		public void setPlaca(String placa) {
			this.placa = placa;
		}
		public Contratado getContratado() {
			return contratado;
		}
		public void setContratado(Contratado contratado) {
			this.contratado = contratado;
		}
		
		public Motorista getMotorista() {
			return motorista;
		}
		public void setMotorista(Motorista motorista) {
			this.motorista = motorista;
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
			Veiculo other = (Veiculo) obj;
			return Objects.equals(id, other.id);
		}
		
		

}
