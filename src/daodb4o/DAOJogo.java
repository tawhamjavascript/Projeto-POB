package daodb4o;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Jogo;

import java.util.List;

public class DAOJogo extends DAO<Jogo> {
    public Jogo read(Object chave) {
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("id").constrain(id);
        List<Jogo> result = q.execute();
        if (result.size() > 0) return result.get(0);
        return null;
    }

    public List<Jogo> jogosComUmaDataEspecifica(String data) {
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("data").constrain(data);
        return q.execute();
    }

    public List<Jogo> matchesOfATeam(String time) {
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("time1").constrain(time).or(q.descend("time2").constrain(time));
        return q.execute();
    }


    public List<Jogo> JogosComMaisDeUmIngesso(){
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.constrain(new Filtro());
        return q.execute();
    }

    class Filtro implements Evaluation {
        public Filtro() {}

        public void evaluate(Candidate candidate) {
            Jogo jogo = (Jogo) candidate.getObject();
            candidate.include(jogo.getIngressos().size() > 0);
        }
    }
}

