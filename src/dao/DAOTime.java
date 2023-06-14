package dao;



import modelo.Time;

import java.util.List;

import jakarta.persistence.TypedQuery;

public class DAOTime extends DAO<Time> {
    public Time read(Object chave) {
    	String name = (String) chave;
		try {
			TypedQuery<Time> q = manager.createQuery("select t from Time t where t.nome=:s", Time.class);
			q.setParameter("s", name);
			return q.getSingleResult();
		}
		catch(Exception e) {
			return null;
		}

    }


    public List<Time> LocalTeam (String local) {
    	TypedQuery<Time> q = manager.createQuery("select t from Time t join t.jogos j where j.local=:s", Time.class);
		q.setParameter("s", local);
		return q.getResultList();



    }
    public List<Time> DataTeam(String data) {
    	TypedQuery<Time> q = manager.createQuery("select t from Time t join t.jogos j where j.data=:s", Time.class);
		q.setParameter("s", data);
		return q.getResultList();

    }

    public List<Time> TimesQuePossuemIngressosDisponiveis() {
    	TypedQuery<Time> q = manager.createQuery("select t from Time t join t.jogos j where j.estoque > 0", Time.class);
		return q.getResultList();

    }


	@Override
	public List<Time> readAll() {
		TypedQuery<Time> q = manager.createQuery("select t from Time t", Time.class);
		return q.getResultList();
	}


}