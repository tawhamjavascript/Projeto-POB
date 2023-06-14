package dao;


import modelo.Ingresso;
import modelo.Time;

import java.util.List;

import jakarta.persistence.TypedQuery;

public class DAOIngresso extends DAO<Ingresso> {
	public Ingresso read (Object chave) {
		int id = (int) chave;
		try {
			TypedQuery<Ingresso> q = manager.createQuery("select i from Ingresso i where i.id=:d", Ingresso.class);
			q.setParameter('d', id);
			return q.getSingleResult();

		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Ingresso> readAll() {
		TypedQuery<Ingresso> q = manager.createQuery("select i from Ingresso i", Ingresso.class);
		return q.getResultList();
	}

	

    
}