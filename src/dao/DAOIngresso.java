package dao;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Ingresso;
import modelo.Time;

import java.util.List;

public class DAOIngresso extends DAO<Ingresso> {
    public Ingresso read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Ingresso.class);
        q.descend("codigo").constrain(id);
        List<Ingresso> result = q.execute();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}