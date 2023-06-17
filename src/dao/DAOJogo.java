package dao;


import modelo.Jogo;

import java.util.List;

import jakarta.persistence.TypedQuery;

public class DAOJogo extends DAO<Jogo> {
	public Jogo read(Object chave) {
		int id = (int) chave;
		try {
			TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where j.id=:d", Jogo.class);
			q.setParameter("d",id);
			return q.getSingleResult();

		}
		catch(Exception e) {
			return null;
		}
		
	}


    public List<Jogo> jogosComUmaDataEspecifica(String data) {
    	TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where j.data=:d", Jogo.class);
		q.setParameter("d", data);
		return q.getResultList();

    }

    public List<Jogo> matchesOfATeam(String time) {
    	TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j join j.times t where t.nome=:s", Jogo.class);
		q.setParameter("s", time);
		return q.getResultList();

    }


    public List<Jogo> JogosComMaisDeUmIngesso(){
    	TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where size(j.ingressos) > 0", Jogo.class);
		return q.getResultList();

    }


	@Override
	public List<Jogo> readAll() {
		TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j", Jogo.class);
		
		return q.getResultList();
	}

}

