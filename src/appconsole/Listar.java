/**
 * SI - POB - Prof. Fausto Ayres
 * Teste das classes do sistema TicketNow
 *
 */

package appconsole;

import modelo.Ingresso;
import modelo.Jogo;
import modelo.Time;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			//---------------------
			//listagem final
			//---------------------
			System.out.println("\n---listar times---");
			for (Time t : Fachada.listarTimes())
				System.out.println(t);

			System.out.println("\n---listar jogos---");
			for (Jogo j : Fachada.listarJogos())
				System.out.println(j);

			System.out.println("\n---listar ingressos---");
			for (Ingresso i : Fachada.listarIngressos())
				System.out.println(i);

			System.out.println("\n---listar jogos na data 02/12/2022---");
			for (Jogo j : Fachada.listarJogos("02/12/2022"))
				System.out.println(j);

			System.out.println("\n---listar valor dos ingressos---");
			for (Ingresso i : Fachada.listarIngressos())
				System.out.println(i.getCodigo()+", valor="+ i.calcularValor());

			System.out.println("\n---listar arrecadacao dos jogos---");
			for (Jogo j : Fachada.listarJogos())
				System.out.println(j.getId() +", arrecadacao="+ j.obterValorArrecadado());

			System.out.println("\n---listar arrecadacao dos times---");
			for (Time t : Fachada.listarTimes())
				System.out.println(t.getNome() + ", arrecadacao="+ t.obterValorArrecadado());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
