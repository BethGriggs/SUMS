/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import up678526.sums.ents.Person;

/**
 *
 * @author up678526
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "SUMS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    public List findUserByEmail(String email) {
        List <Person> results = em
                .createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class)
                .setParameter("email", email)
                .getResultList(); 
        return results;
    }   
}
