/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import up678526.sums.bus.PersonService;
import up678526.sums.ents.Person;

/**
 *
 * @author up678526
 */
@Named(value = "personBean")
@RequestScoped
public class PersonBean {

    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean() {
      
    }
    
    private String email; 
    private String password;

    private String type; 
    
    @EJB 
    private PersonService personService; 
    
    public PersonService getService() {
        return this.personService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    public void register() {
    
        Person user = new Person();
        
        user.setPassword(this.password);
        user.setEmail(this.email);
    }
    
    
}
