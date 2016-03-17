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
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.IdeaService;
import up678526.sums.bus.exception.AuthorisationException;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Person;

/**
 * controller providing idea CRUD functionality
 *
 * @author up678526
 */
@Named(value = "ideaBean")
@SessionScoped
public class IdeaBean implements Serializable {

    /**
     * Creates a new instance of IdeaBean
     */
    public IdeaBean() {
    }

    @ManagedProperty(value = "#{param.id}")
    private String selectedId;
    private Idea idea = null;
    private String title;
    private String description;
    private String tags;

    @EJB
    private IdeaService ideaService;

    private List<Idea> allIdeas;

    /**
     * adds a new idea to the database
     *
     * @return index view
     */
    public String create() {

        // create the idea
        Idea newIdea = new Idea();
        newIdea.setTitle(this.title);
        newIdea.setDescription(this.description);
        newIdea.setTags(this.tags);
        newIdea.setOwner(getCurrentUser());

        try {
            ideaService.addIdea(getCurrentUser(), newIdea);
        } catch (AuthorisationException ex) {
            Logger.getLogger(IdeaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/index?faces-redirect=true";
    }

    /**
     * updated the current idea
     *
     * @return
     */
    public String update() {
        try {
            ideaService.update(getCurrentUser(), idea);
        } catch (AuthorisationException ex) {
            Logger.getLogger(IdeaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/idea/view?faces-redirect=true";
    }

    /**
     * deletes the current idea
     *
     * @return
     */
    public String delete() {
        try {
            ideaService.remove(getCurrentUser(),idea);
        } catch (AuthorisationException ex) {
            Logger.getLogger(IdeaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/index?faces-redirect=true";
    }

    /**
     * retrieves the idea based on managed property selectedId
     */
    public void retrieve() {
        idea = ideaService.getIdea(Long.parseLong(selectedId));
    }

    /**
     * assigns the idea to the current (student) user
     */
    public void assignIdea() {
        try {
            ideaService.assignIdeaToStudent(getCurrentUser(), idea);
        } catch (AuthorisationException ex) {
            Logger.getLogger(IdeaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* getters and setters */

    /**
     *
     * @return managed property selectedId
     */
    public String getSelectedId() {
        return selectedId;
    }

    /**
     *
     * @param selectedId
     */
    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    /**
     *
     * @return idea
     */
    public Idea getIdea() {
        return idea;
    }

    /**
     *
     * @param idea
     */
    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return tags
     */
    public String getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     *
     * @return list all available ideas
     */
    public List<Idea> getAllIdeas() {
        allIdeas = ideaService.getAllAvailableIdeas();
        return allIdeas;
    }

    /**
     *
     * @return current user object
     */
    public Person getCurrentUser() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return (Person) externalContext.getSessionMap().get("user");
    }
}
