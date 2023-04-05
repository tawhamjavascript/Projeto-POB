package appconsole;

import regras_negocio.Fachada;

/**
 * SI - POB - Prof. Fausto Ayres
 * Teste das classes do sistema TicketNow
 *
 */
public class Cadastrar {
	public static void main(String[] args) {
		Fachada.inicializar();
		try {
			Fachada.criarTime("brasil", "br");
			Fachada.criarTime("argentina", "ar");
			Fachada.criarTime("chile", "ch");	
			Fachada.criarTime("bolivia", "bo");	
		}
		catch(Exception ex) {
			System.out.println("problema ao criar time-->"+ex.getMessage());
		}

		try {
			Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "brasil", "argentina");
			Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "chile", "bolivia");
			Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "brasil", "chile");
			Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "argentina", "bolivia");
		}
		catch(Exception ex) {
			System.out.println("problema ao criar jogo-->"+ex.getMessage());
		}

		try {
			Fachada.criarIngressoIndividual(1);		//id do jogo
			Fachada.criarIngressoIndividual(2);		//id do jogo
			Fachada.criarIngressoIndividual(3);		//id do jogo
			Fachada.criarIngressoIndividual(4);		//id do jogo
		}
		catch(Exception ex) {
			System.out.println("problema ao criar ingresso individual-->"+ex.getMessage());
		}

		try {
			Fachada.criarIngressoGrupo(new int[]{1,3} );		//id dos jogos
			Fachada.criarIngressoGrupo(new int[]{2,4} );		//id dos jogos
			Fachada.criarIngressoGrupo(new int[]{1,2,3} );		//id dos jogos
		}
		catch(Exception ex) {
			System.out.println("problema ao criar ingresso grupo-->"+ex.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}
}
