/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.PersonService;
import up678526.sums.ents.Person;

/**
 *
 * @author up678526
 */
@Named(value = "personBean")
@SessionScoped
public class PersonBean implements Serializable {

    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean() {

    }
    private Person current;     
    private String email;
    private String password;

    private boolean isStudent = true;

    @EJB
    private PersonService personService;

    public Person getCurrent() {
        return current;
    }

    public void setCurrent(Person current) {
        this.current = current;
    }

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

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public void register() {

        Person user = new Person();

        user.setPassword(this.password);
        user.setEmail(this.email);

        if (isStudent) {
            user.setType("STUDENT");
        } else {
            user.setType("STAFF");
        }
        personService.createNewUser(user);
    }
    
     /**
     * Attempt login
     * @return 
     */
    public String login(){ 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        boolean res= personService.userExists(email);
        if (res) {
            //validate credentials
            current = personService.validateCredentials(email, password);
            if (current != null){
                externalContext.getSessionMap().put("user", current);
                return "/index.html?faces-redirect=true";
            }
            else {
                return "/index.html?faces-redirect=true";
            }    
        }
        else {
            return "";
        }
    }
    
    public String logout(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        current = null;
        return "/index.xhtml?faces-redirect=true";
    }
}
