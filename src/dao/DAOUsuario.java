/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package dao;

import java.util.List;

import jakarta.persistence.TypedQuery;
import modelo.Usuario;

public class DAOUsuario extends DAO<Usuario>{

	public Usuario read (Object chave) {
		String email = (String) chave;
		try {
			TypedQuery<Usuario> q = manager.createQuery("select u from Usuario u where u.email=:s", Usuario.class);
			q.setParameter("s", email);
				//casting para o tipo da chave
			return q.getSingleResult();

		}
		catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		TypedQuery<Usuario> q = manager.createQuery("select u from Usuario u", Usuario.class);
		return q.getResultList();
	}
	




	//--------------------------------------------
	//  consultas
	//--------------------------------------------

}

