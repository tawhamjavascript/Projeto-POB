/**
 * SI - POB - Prof. Fausto Ayres
 * Teste das classes do sistema TicketNow
 *
 */

package appconsole;

import regras_negocio.Fachada;

public class TesteExcecao {

	public static void main(String[] args) {
		
		Fachada.inicializar();
		
		System.out.println("\n-------TESTE DE EXCE��ES LAN�ADAS PELOS METODOS DA FACHADA--------");

		try {
			Fachada.criarTime("teste1", "origem1");
			Fachada.criarTime("teste2", "origem2");
			Fachada.criarJogo("01/01/2000","teste",10, 1.0, "teste1", "teste3");
			System.out.println("**1--->Nao lan�ou exce��o para: criar jogo com times inexistentes "); 
		}catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}

		try {
			Fachada.criarJogo("01/01/2000","teste",1000, 1.0, "teste3", "teste1");
			System.out.println("**2--->Nao lan�ou exce��o para: criar jogo com times inexistentes "); 
		}catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}

		try {
			Fachada.criarJogo("01/01/2000","teste",0, 1.0, "teste1", "teste2");
			System.out.println("**3--->Nao lan�ou exce��o para: criar jogo com estoque 0 "); 
		}catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}

		try {
			Fachada.criarJogo("01/01/2000","teste",1000, 0.0, "teste1", "teste2");
			System.out.println("**4--->Nao lan�ou exce��o para: criar jogo com preco 0 "); 
		}catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}

		try {
			Fachada.criarIngressoIndividual(999);
			System.out.println("**5--->Nao lan�ou exce��o para: criar ingresso para jogo inexistente "); 
		}catch (Exception e) {System.out.println("5ok--->"+e.getMessage());}

		try {
			Fachada.criarIngressoGrupo(new int[]{77,88,99} );		//id dos jogos
			System.out.println("**6--->Nao lan�ou exce��o para: criar ingresso para jogos inexistentes  "); 
		}catch (Exception e) {System.out.println("6ok--->"+e.getMessage());}

		try {
			Fachada.apagarIngresso(999999999);
			System.out.println("**7--->Nao lan�ou exce��o para: cancelar ingresso inexistente"); 
		}catch (Exception e) {System.out.println("7ok--->"+e.getMessage());}
		
		Fachada.finalizar();
	}

}


