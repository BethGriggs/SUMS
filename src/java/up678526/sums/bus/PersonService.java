/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Person;
import up678526.sums.pers.IdeaFacade;
import up678526.sums.pers.PersonFacade;

/**
 *
 * @author up678526
 */
@Stateless
public class PersonService {

    public PersonService() {
    }

    @EJB
    private PersonFacade personFacade;
    
    @EJB
    private IdeaFacade ideaFacade; 
    
    public boolean userExists(String email) {
     
        List<Person> persons =  personFacade.findUserByEmail(email);
        return !persons.isEmpty();
    }
    
    public Person validateCredentials(String email, String password){
        List<Person> persons =  personFacade.findUserByEmail(email);
        Person person = persons.get(0);
        //if (person.getEmail().equals(email) &&person.getPassword().equals(password)){
            return person;
        //}
        //return null;
    }
   
    public void createNewUser(Person person) {
        personFacade.create(person);
    }
    
    public List<Idea> getOwnedIdeas(Person person){
        return ideaFacade.findIdeasByOwner(person);
    }
}
