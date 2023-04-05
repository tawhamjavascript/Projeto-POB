/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package modelo;

public abstract class Ingresso  {
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
