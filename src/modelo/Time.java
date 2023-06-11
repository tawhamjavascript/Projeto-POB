/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Time {
	@Id
	private String nome;
	private String origem;
	
	@OneToMany(mappedBy="")
	private ArrayList<Jogo> jogos = new ArrayList<>();

	
	public Time(String nome, String origem) {
		super();
		this.nome = nome;
		this.origem = origem;
	}

	public double obterValorArrecadado() {
		double soma=0;
		for(Jogo j : jogos)
			soma = soma + j.obterValorArrecadado();
		
		return soma;
	}

	public void adicionar(Jogo j) {
		jogos.add(j);
	}
	public void remover(Jogo j) {
		jogos.remove(j);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public ArrayList<Jogo> getJogos () {
		return this.jogos;
	}
	
		@Override
	public String toString() {
		String texto = "nome=" + nome + ", origem=" + origem ;
		
		texto += 	"\njogos: " ;
		for(Jogo j : jogos)
			texto += j.getId() +"=" + j.getData()+ "," + j.getLocal() +"  ";
		return texto;
	}

	
	
}
