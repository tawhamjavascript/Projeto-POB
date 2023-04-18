/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package regras_negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import daodb4o.*;
import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import modelo.Usuario;

public class Fachada {
	private Fachada() {}

	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOTime daotime = new DAOTime();
	private static DAOJogo daojogo = new DAOJogo();
	private static DAOIngresso daoingresso = new DAOIngresso();
	private static DAOIngressoIndividual daoingressoindividual = new DAOIngressoIndividual();
	private static DAOIngressoGrupo daoingressogrupo = new DAOIngressoGrupo();


	public static Usuario logado;

	public static void inicializar(){
		DAO.open();

	}

	public static void finalizar(){
		DAO.close();

	}


	public static List<Time> listarTimes() {
		//retorna todos os times
		DAO.begin();
		List<Time> result = daotime.readAll();
		DAO.commit();
		return result;

	}

	public static List<Jogo> listarJogos() {
		//retorna todos os jogos
		DAO.begin();
		List<Jogo> result = daojogo.readAll();
		DAO.commit();
		return result;
	}

	public static List<Usuario> listarUsuarios() {
		DAO.begin();
		List<Usuario> result = daousuario.readAll();
		DAO.commit();
		return result;

	}

	public static List<Ingresso> listarIngressos() {
		DAO.begin();
		List<Ingresso> result = daoingresso.readAll();
		DAO.commit();
		return result;
	}

	public static List<Jogo> listarJogos(String data) {
		DAO.begin();

		//retorna os jogos na data fornecida (query)
		List<Jogo> result = daojogo.jogosComUmaDataEspecifica(data);
		DAO.commit();
		return result;
	}

	public static Ingresso localizarIngresso(int codigo) {
		//retorna o ingresso com o c�digo fornecido
		DAO.begin();
		Ingresso result = daoingresso.read(codigo);
		DAO.commit();
		return result;

	}

	public static Jogo localizarJogo(int id) {
		DAO.begin();
		Jogo jogo = daojogo.read(id);
		DAO.commit();
		return jogo;
	}

	public static Usuario criarUsuario(String email, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(email);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + email);
		usu = new Usuario(email, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}

	public static Usuario validarUsuario(String email, String senha) {
		DAO.begin();
		Usuario usu = daousuario.read(email);
		if (usu==null)
			return null;

		if (! usu.getSenha().equals(senha))
			return null;

		DAO.commit();
		return usu;
	}

	public static Time criarTime(String nome, String origem) throws Exception {
		DAO.begin();
		//verificar regras de negocio
		//criar o time
		if (daotime.read(nome) == null) {
			Time time = new Time(nome, origem);
			daotime.create(time);
			DAO.commit();
			return time;
		}
		throw new Exception("Time j� existe");
	}

	public static Jogo criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		DAO.begin();
		//verificar regras de negocio
		if (!nometime1.equals(nometime2)) {
			Jogo jogo = new Jogo(data, local, estoque, preco);

			//localizar time1 e time2
			Time time1 = daotime.read(nometime1);

			Time time2 = daotime.read(nometime2);

			if (time1 == null || time2 == null) {
				throw new Exception("Time n�o existe");
			}
			
			if (estoque == 0) {
				throw new Exception ("O estoque não pode ser 0");
			}
			
			if (preco == 0) {
				throw new Exception ("O preço não pode ser 0");
			}

			else {
				jogo.setTime1(time1);
				jogo.setTime2(time2);
				time1.adicionar(jogo);
				time2.adicionar(jogo);
				jogo.setId(daojogo.gerarId());
				daojogo.create(jogo);
				daotime.update(time1);
				daotime.update(time2);
				DAO.commit();
				return jogo;


			}

		}
		throw new Exception("Times com nomes iguais");


	}

	public static IngressoIndividual criarIngressoIndividual(int id) throws Exception{
		DAO.begin();
		//verificar regras de negocio
		//gerar codigo aleat�rio

		Jogo jogo = daojogo.read(id);
		if (jogo == null) {
			throw new Exception("Jogo n�o existe");

		}

		IngressoIndividual ingressoIndividual;
		int codigo;

		//verificar unicididade do codigo no sistema
		do {
			codigo = new Random().nextInt(1000000);
			ingressoIndividual = (IngressoIndividual) daoingressoindividual.read(codigo);

		} while (ingressoIndividual != null);

		//criar o ingresso individual

		ingressoIndividual = new IngressoIndividual(codigo);
		//relacionar este ingresso com o jogo e vice-versa
		ingressoIndividual.setJogo(jogo);
		jogo.adicionar(ingressoIndividual);
		jogo.setEstoque(jogo.getEstoque()-1);
		daoingressoindividual.create(ingressoIndividual);
		daojogo.update(jogo);
		//gravar ingresso no banco
		DAO.commit();

		return ingressoIndividual;

	}

	public static IngressoGrupo criarIngressoGrupo(int[] ids) throws Exception{
		DAO.begin();
		ArrayList<Jogo> jogos = new ArrayList<Jogo>();
		Jogo jogo;
		for(int id: ids) {
			jogo = daojogo.read(id);
			if (jogo == null) {
				throw new Exception("Um dos jogos informados n�o existe");
			}

			jogos.add(jogo);

		}


		//gerar codigo aleat�rio
		IngressoGrupo ingressoGrupo;
		int codigo;

		//verificar unicididade no sistema
		do {
			codigo = new Random().nextInt(1000000);
			ingressoGrupo = (IngressoGrupo) daoingressogrupo.read(codigo);


		} while (ingressoGrupo != null);

		//criar o ingresso grupo

		ingressoGrupo = new IngressoGrupo(codigo);

		//relacionar este ingresso com os jogos indicados e vice-versa
		for(Jogo j: jogos) {
			j.adicionar(ingressoGrupo);
			j.setEstoque(j.getEstoque()-1);
			ingressoGrupo.adicionar(j);
			daojogo.update(j);
		}

		daoingressogrupo.create(ingressoGrupo);
		DAO.commit();
		return ingressoGrupo;
	}

	public static void apagarIngresso(int codigo) throws Exception {
		DAO.begin();
		//o codigo refere-se a ingresso individual ou grupo
		//verificar regras de negocio
		//remover o relacionamento entre o ingresso e o(s) jogo(s) ligados a ele

		Ingresso ingresso = daoingresso.read(codigo);

		if (ingresso == null) {
			throw new Exception("Ingresso n�o existe");
		}

		if (ingresso instanceof IngressoGrupo grupo) {
			ArrayList<Jogo> jogos = grupo.getJogos();
			for (Jogo j : jogos) {
				j.remover(grupo);
				j.setEstoque(j.getEstoque()+1);
				//grupo.remover(j);
				daojogo.update(j);


			}
		} else if (ingresso instanceof IngressoIndividual individuo) {
			Jogo jogo = individuo.getJogo();
			jogo.remover(individuo);
			jogo.setEstoque(jogo.getEstoque()+1);
			daojogo.update(jogo);
		}

		daoingresso.delete(ingresso);
		//apagar ingresso no banco

		DAO.commit();
	}

	public static void apagarTime(String nome) throws Exception {

		//verificar regras de negocio
		DAO.begin();

		Time time = daotime.read(nome);
		if (time == null) {
			throw new Exception("Time n�o existe");

		}

		if(time.getJogos().size() > 0) {
			throw new Exception("Time possui jogos");
		}
		//apagar time no banco
		daotime.delete(time);
		DAO.commit();
	}

	public static void apagarJogo(int id) throws Exception{
		DAO.begin();
		Jogo jogo = daojogo.read(id);
		if (jogo == null) {
			throw new Exception("Jogo não existe");

		}
		if (jogo.getIngressos().size() > 0) {
			throw new Exception("Jogo possui ingressos");
		}

		daojogo.delete(jogo);
		//verificar regras de negocio
		//apagar jogo no banco
		DAO.commit();
	}

	/**********************************
	 * 5 Consultas
	 **********************************/
	public static List<Time> timesQueJogaramEmUmLocal(String local) throws Exception{
		DAO.begin();
		List<Time> times = daotime.LocalTeam(local);
		if (times.size() == 0) {
			throw new Exception("N�o existe times que jogaram no local informado");
		}
		DAO.commit();
		return times;
	}

	public static List<Time> timesQueJogaramEmUmaData(String data) throws Exception {
		DAO.begin();
		List<Time> times = daotime.DataTeam(data);
		if (times.size() == 0) {
			throw new Exception("N�o existe times que jogaram nessa data");
		}
		DAO.commit();
		return times;
 	}
	public static List<Time> timesQuePossuemIngressosDisponiveis() throws Exception {
		DAO.begin();
		List<Time> times = daotime.TimesQuePossuemIngressosDisponiveis();
		if (times.size() == 0) {
			throw new Exception("N�o possuem times com jogos dispon�veis");
		}
		DAO.commit();
		return times;
	}

	public static List<Jogo> jogosDeUmTimeEspecifico(String time) throws Exception {
		DAO.begin();
		List<Jogo> jogos = daojogo.matchesOfATeam(time);
		System.out.println(jogos);
		System.out.println(time);
		if (jogos.size() == 0) {
			throw new Exception("N�o existe jogos com esse time");
		}
		DAO.commit();
		return jogos;
	}

	public static List<Jogo> jogosComMaisDeUmIngresso() throws Exception {
		DAO.begin();
		List<Jogo> jogos = daojogo.JogosComMaisDeUmIngesso();
		if (jogos.size() == 0) {
			throw new Exception("N�o existe jogos com mais de um ingresso");
		}
		DAO.commit();
		return jogos;
	}
}
