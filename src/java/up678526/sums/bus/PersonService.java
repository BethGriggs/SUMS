/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import up678526.sums.ents.Person;
import up678526.sums.pers.PersonFacade;

/**
 *
 * @author up678526
 */
@Stateless
public class PersonService {
    
    public PersonService() {}
    
    @EJB
    private PersonFacade personFacade; 
    
    protected PersonFacade getFacade(){
        return this.personFacade; 
    }
    
    public Person find(Long id){
        return getFacade().find(id); 
    }
    
    public void create(Person person){
        getFacade().create(person);
    }
    
    public List<Person> findAll(){
        return getFacade().findAll(); 
    }
    
    public Person update(Person person) {
        return getFacade().edit(person);
    }
    
   public void remove(Person person){
       getFacade().remove(person);
   }
    
}
