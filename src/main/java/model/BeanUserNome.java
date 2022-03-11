package model;

public class BeanUserNome {

	private String nome;
	private String numero;
	private String email;
	
	public BeanUserNome() {
	}

	public BeanUserNome(String nome, String numero, String email) {
		this.nome = nome;
		this.numero = numero;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", Número: " + getNumero() + ", E-mail: " + getEmail();
	}
	
	
	
}
