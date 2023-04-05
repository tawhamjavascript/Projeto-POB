/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Usuario;

public class DAOUsuario extends DAO<Usuario>{

	public Usuario read (Object chave){
		String email = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("email").constrain(email);
		List<Usuario> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}



	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public Usuario searchPerEmail(String email) {
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("email").constrain(email);
		List<Usuario> result = q.execute();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}

