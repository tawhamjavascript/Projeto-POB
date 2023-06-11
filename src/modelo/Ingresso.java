/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public abstract class Ingresso  {
	@Id
	protected int codigo;
	
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
