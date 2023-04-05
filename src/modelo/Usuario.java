package modelo;

public class Usuario {
	private String email;
	private String senha;
	
	public Usuario(String nome, String senha) {
		super();
		this.email = nome;
		this.senha = senha;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + "]";
	}
	
	
}
