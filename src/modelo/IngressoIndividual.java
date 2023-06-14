/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class IngressoIndividual extends Ingresso {
	@ManyToMany(mappedBy = "ingressos",
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	

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
