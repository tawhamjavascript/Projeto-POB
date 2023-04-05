/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
package modelo;

public class IngressoIndividual extends Ingresso {
	private Jogo jogo;
	

	public IngressoIndividual(int codigo) {
		super(codigo);
	}

	public double calcularValor() {
		return 1.2 * jogo.getPreco();
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
		jogo.setEstoque(jogo.getEstoque() - 1 );
	}

	@Override
	public String toString() {
		return "codigo=" + codigo + ", jogo=" + jogo.getId();
	}
	
	
	
}
