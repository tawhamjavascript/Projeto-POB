/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Jogo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String data;
	private String local;
	private int estoque;
	private double preco;
	
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
			
	})
	private ArrayList<Time> times = new ArrayList<Time>();
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REMOVE,
	})
	
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();

	public Jogo(String data, String local, int estoque, double preco) {
		//id ser� gerado pelo banco;
		this.data = data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
	}
	
	public Jogo() {
		//id ser� gerado pelo banco;
	}
	public void adicionar(Ingresso i){
		ingressos.add(i);
	}
	public void remover(Ingresso i){
		ingressos.remove(i);
	}

	public Ingresso localizar(int codigo){
		for(Ingresso i : ingressos){
			if(i.getCodigo() == codigo)
				return i;
		}
		return null;
	}

	public double obterValorArrecadado() {
		double soma=0;
		for(Ingresso i : ingressos)
			soma = soma + i.calcularValor();

		return soma;
	}

	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Time getTime1() {
		return times.get(0);
	}

	public Time getTime2() {
		return times.get(1);
	}
	
	public void setTime1(Time time1) {
		this.times.add(0, time1);
	}

	public void setTime2(Time time2) {
		this.times.add(1, time2);
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Ingresso ingresso) {
		this.ingressos.add(ingresso);

	}
	@Override
	public String toString() {

		String texto = "id=" + id + ", data=" + data + ", local=" + local + ", estoque=" + estoque + ", preco=" + preco
				+ ", time1=" + this.getTime1().getNome() + " x time2=" + this.getTime2().getNome();

		texto += "\ningressos:";
		for(Ingresso i : ingressos)
			texto += i.getCodigo() + ",";
		return texto;
	}


}
