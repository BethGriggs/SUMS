/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.PersonService;
import up678526.sums.bus.exception.AuthenticationException;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Organisation;
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
    private String type;
    private Organisation organisation;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrentUserEmail() {
        return current.getEmail();
    }

    public String getCurrentUserType() {
        if (current != null) {
            return current.getType();
        } else {
            return null;
        }
    }

    public List<Idea> getOwnedIdeas() {
        return personService.getOwnedIdeas(current);
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
     
    public String register() {

        Person user = new Person();

        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setType(this.type.toUpperCase());

        personService.createNewUser(user);
        return "/login?faces-redirect=true";
    }

    /**
     * Attempt login
     *
     * @return
     */
    public String login() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            //validate user credentials
            current = personService.validateCredentials(email, password);
        } catch (AuthenticationException ex) {
            FacesContext.getCurrentInstance().addMessage("loginError", new FacesMessage("Failed to login: ", ex.getMessage()));
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current != null) {
            externalContext.getSessionMap().put("user", current);
        } else {
            return null;
        }

        return "/index?faces-redirect=true";
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getSessionMap().remove("user");
        externalContext.invalidateSession();
        current = null;
        return "/index?faces-redirect=true";
    }
    
    public String assignOrganisation(){
       personService.assignOrganisation(current, organisation);
       return "/person/view?faces-redirect=true";
    }
}
