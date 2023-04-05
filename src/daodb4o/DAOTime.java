package daodb4o;


import com.db4o.query.Query;
import modelo.Time;

import java.util.List;

public class DAOTime extends DAO<Time> {
    public Time read(Object chave) {
        String name = (String) chave;
        Query q = manager.query();
        q.constrain(Time.class);
        q.descend("nome").constrain(name);
        List<Time> result = q.execute();
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public List<Time> allTeamsOfOrigin(String origin) {
        Query q = manager.query();
        q.constrain(Time.class);
        q.descend("origem").constrain(origin);
        List<Time> result = q.execute();
        if (result.size() > 0) {
            return result;
        }
        return null;

    }
}
