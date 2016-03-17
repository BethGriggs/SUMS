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
package up678526.sums.ctrl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import up678526.sums.bus.IdeaService;
import up678526.sums.bus.PersonService;
import up678526.sums.bus.exception.AuthenticationException;
import up678526.sums.bus.exception.BusinessException;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Organisation;
import up678526.sums.ents.Person;

/**
 * controller for user management
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

    @EJB
    private IdeaService ideaService;

    /**
     * creates a new user, checks if email is already in use
     *
     * @return login view if successful, current view if unsuccessful
     */
    public String register() {

        // create the new user
        Person user = new Person();
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setType(this.type.toUpperCase());

        try {
            personService.createNewUser(user);
            return "/login?faces-redirect=true";

        } catch (BusinessException ex) {
            // catches exception and provides error message if email is already in use
            FacesContext.getCurrentInstance().addMessage("registrationError", new FacesMessage("Failed to register: ", ex.getMessage()));
            return "";
        }
    }

    /**
     * attempt login, set current context if successful
     *
     * @return index page if successful, login page if unsuccessful
     */
    public String login() {
        try {
            // validate user credentials
            current = personService.validateCredentials(email, password);
        } catch (AuthenticationException ex) {
            // catch login failure
            getCurrentInstance().addMessage("loginError", new FacesMessage("Failed to login: ", ex.getMessage()));
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current != null) {
            // put the current user to the session map
            getCurrentInstance().getExternalContext().getSessionMap().put("user", current);
        } else {
            return "";
        }

        return "/index?faces-redirect=true";
    }

    /**
     * logs the user out, invalidates session
     *
     * @return
     */
    public String logout() {
        getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        getCurrentInstance().getExternalContext().invalidateSession();
        current = null;
        return "/index?faces-redirect=true";
    }

    /**
     * assigns the user to an organisation
     *
     * @return
     */
    public String assignOrganisation() {
        personService.assignOrganisation(current, organisation);
        return "/person/view?faces-redirect=true";
    }

    /**
     * obtains the users assigned idea
     *
     * @return
     */
    public Idea getAssignedIdea() {
        if (personService.getAssignedIdea(current).isEmpty()) {
            return null;
        } else {
            return personService.getAssignedIdea(current).get(0);
        }
    }

    /**
     * allows the user to deselect their current idea
     *
     * @return
     */
    public String deselectIdea() {
        if (getAssignedIdea() != null) {
            ideaService.deselectIdea(getAssignedIdea());
        }
        return "/person/view?faces-redirect=true";
    }

    /* getters and setters */

    /**
     *
     * @return current user
     */
    public Person getCurrent() {
        return current;
    }

    /**
     *
     * @param current
     */
    public void setCurrent(Person current) {
        this.current = current;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return organistion
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     *
     * @param organisation
     */
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    /**
     *
     * @return all ideas owned by current user
     */
    public List<Idea> getOwnedIdeas() {
        return personService.getOwnedIdeas(current);
    }

    /**
     *
     * @return list of all ideas created by the user
     */
    public List<Idea> getUserIdeas() {
        return personService.getOwnedIdeas(current);
    }

    /**
     *
     * @return current instance
     */
    public FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }
}
