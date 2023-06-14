/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;




import jakarta.persistence.Entity;


@Entity
public class IngressoIndividual extends Ingresso {
	
	
	public IngressoIndividual(int codigo) {
		super(codigo);
	}

	public IngressoIndividual() {
	}

	public double calcularValor() {
		return 1.2 * jogos.get(0).getPreco();
	}

	public Jogo getJogo() {
		return jogos.get(0);
	}

	public void setJogo(Jogo jogo) {
		this.jogos.add(jogo);
		jogo.setEstoque(jogo.getEstoque() - 1 );
	}

	@Override
	public String toString() {
		return "codigo=" + this.getCodigo() + ", jogo=" + this.getJogo().getId();
	}
	
	
	
}
