/*
 *
 */
package up678526.sums.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Person;

/**
 *
 * @author up678526
 */
@Stateless
public class IdeaFacade extends AbstractFacade<Idea> {

    @PersistenceContext(unitName = "SUMS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdeaFacade() {
        super(Idea.class);
    }
    
    public List<Idea> findIdeasByOwner(Person person){
        List <Idea> results = em
                .createQuery("SELECT i FROM Idea i WHERE i.owner.id = :id", Idea.class)
                .setParameter("id", person.getId())
                .getResultList(); 
        return results;  
    }
}
