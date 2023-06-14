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
public class IngressoGrupo extends Ingresso {
	
	@ManyToMany(mappedBy= "ingressos",
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE,
			})
	private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	
	public IngressoGrupo(int codigo) {
		super(codigo);
	}
	public IngressoGrupo() {
	}

	public double calcularValor() {
		double soma=0;
		for(Jogo j : jogos)
			soma = soma + j.getPreco();
		
		return 0.9 * soma;
	}
	
	public void adicionar(Jogo j){
		jogos.add(j);
		j.setEstoque(j.getEstoque() - 1 );	
	}
	public void remover(Jogo j){
		jogos.remove(j);
	}
	public Jogo localizar(int id){
		for(Jogo j : jogos){
			if(j.getId() == id)
				return j;
		}
		return null;
	}

	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	@Override
	public String toString() {
		String texto = "codigo=" + this.getCodigo() + ", jogos:";
		
		for(Jogo j : jogos)
			texto += j.getId() + ",";
		
		return texto;
	}
	
	
}
