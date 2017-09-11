package agenda.entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EManager implements java.io.Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
    private static EntityManager em = emf.createEntityManager();

    public EManager() {
    }

    public static EntityManager getInstance() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

}
