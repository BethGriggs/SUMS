/* The MIT License (MIT)
 *
 * Copyright (c) 2016 UP678526
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. */
package up678526.sums.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Person;

/**
 * facade class for interacting with idea entities
 * @author up678526
 */
@Stateless
public class IdeaFacade extends AbstractFacade<Idea> {

    @PersistenceContext(unitName = "SUMS")
    private EntityManager em;

    /**
     *
     * @return entity manager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * constructor for idea facade
     */
    public IdeaFacade() {
        super(Idea.class);
    }
    
    /**
     *
     * @return list of all unassigned ideas
     */
    public List<Idea> findAllUnassignedIdeas(){
              List <Idea> results = em
                .createQuery("SELECT i FROM Idea i WHERE i.assigned = :assigned", Idea.class)
                .setParameter("assigned", Boolean.FALSE)
                .getResultList(); 
        return results;  
    }
    
    /**
     *
     * @param person
     * @return list of all ideas owned by the specified user
     */
    public List<Idea> findIdeasByOwner(Person person){
        List <Idea> results = em
                .createQuery("SELECT i FROM Idea i WHERE i.owner.id = :id", Idea.class)
                .setParameter("id", person.getId())
                .getResultList(); 
        return results;  
    }
    
    /**
     *
     * @param student
     * @return specified users assigned idea
     */
    public List<Idea> findUserAssignedIdea(Person student){
        List <Idea> results = em
                .createQuery("SELECT i FROM Idea i WHERE i.student.id = :id", Idea.class)
                .setParameter("id", student.getId())
                .getResultList();
        return results;
    }
}
