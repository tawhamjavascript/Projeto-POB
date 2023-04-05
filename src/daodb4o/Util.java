
    /**********************************
     * IFPB - Curso Superior de Tec. em Sist. para Internet
     * POB - Persistencia de Objetos
     * Prof. Fausto Ayres
     *
     */

package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import modelo.Usuario;

public class Util {
        private static ObjectContainer manager=null;
        private static int contador; //contador de pedidos de conexao

        public static ObjectContainer conectarDb4oLocal(){
            contador++;
            if (manager != null)
                return manager; //ja tem uma conexao

//---------------------------------------------------------------
//configurar, criar e conectar banco local (na pasta do projeto
//---------------------------------------------------------------

            EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration();
            config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...

// habilitar cascata na altera??o, remo??o e leitura
            config.common().objectClass(IngressoGrupo.class).cascadeOnDelete(false);
            config.common().objectClass(IngressoGrupo.class).cascadeOnUpdate(true);
            config.common().objectClass(IngressoGrupo.class).cascadeOnActivate(true);


//---------------------------------------------------------------------

            config.common().objectClass(IngressoIndividual.class).cascadeOnDelete(false);
            config.common().objectClass(IngressoIndividual.class).cascadeOnUpdate(true);
            config.common().objectClass(IngressoIndividual.class).cascadeOnActivate(true);
//-----------------------------------------------------------------------


            config.common().objectClass(Jogo.class).cascadeOnDelete(false);
            config.common().objectClass(Jogo.class).cascadeOnUpdate(true);;
            config.common().objectClass(Jogo.class).cascadeOnActivate(true);

// ---------------------------------------------------------------------
            config.common().objectClass(Time.class).cascadeOnDelete(false);
            config.common().objectClass(Time.class).cascadeOnUpdate(true);;
            config.common().objectClass(Time.class).cascadeOnActivate(true);

// --------------------------------------------------------------------

            config.common().objectClass(Usuario.class).cascadeOnDelete(false);
            config.common().objectClass(Usuario.class).cascadeOnUpdate(true);
            config.common().objectClass(Usuario.class).cascadeOnActivate(true);

//------------------------------------------------------------------

// criar indices (opcional) sobre campos de busca
            config.common().objectClass(IngressoIndividual.class).objectField("codigo").indexed(true);
            config.common().objectClass(IngressoGrupo.class).objectField("codigo").indexed(true);
            config.common().objectClass(Jogo.class).objectField("id").indexed(true);
            config.common().objectClass(Time.class).objectField("nome").indexed(true);
            config.common().objectClass(Usuario.class).objectField("email").indexed(true);


// nivel de profundidade do grafo para leitura e atualiza??o
            config.common().objectClass(IngressoIndividual.class).updateDepth(5);
            config.common().objectClass(IngressoIndividual.class).minimumActivationDepth(5);
            config.common().objectClass(IngressoGrupo.class).updateDepth(5);
            config.common().objectClass(IngressoGrupo.class).minimumActivationDepth(5);
            config.common().objectClass(Jogo.class).updateDepth(5);
            config.common().objectClass(Jogo.class).minimumActivationDepth(5);
            config.common().objectClass(Time.class).updateDepth(5);
            config.common().objectClass(Time.class).minimumActivationDepth(5);
            config.common().objectClass(Usuario.class).updateDepth(5);
            config.common().objectClass(Usuario.class).minimumActivationDepth(5);

//conexao local
            manager = Db4oEmbedded.openFile(config, "banco.db4o");
            return manager;
        }

        public static void desconectar() {
            if (manager != null && contador > 0) {
                manager.close();
                manager = null;
            }
        }
}

