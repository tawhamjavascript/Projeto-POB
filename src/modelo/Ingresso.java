/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Ingresso  {
	@Id
	private int codigo;
	@ManyToMany(mappedBy= "ingressos",
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE,
			})
	protected ArrayList<Jogo> jogos = new ArrayList<Jogo>();
	
	public Ingresso() {
	}
	public Ingresso(int codigo) {
		this.codigo = codigo;
	}

	public abstract double calcularValor();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	

}
