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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.IdeaService;
import up678526.sums.bus.PersonService;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Person;

/**
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
   
    @ManagedProperty(value="#{param.id}")
    private String selectedId;
    private Idea idea = null;
    private String title;
    private String description;
    private String tags;
    
    @EJB
    private IdeaService ideaService;

    @EJB
    private PersonService personService;
    
    private List<Idea> allIdeas;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public IdeaService getService() {
        return this.ideaService;
    }
    
    // create Entity from properties 
    public List<Idea> getAllIdeas() {
        allIdeas = ideaService.getAllAvailableIdeas();
        return allIdeas;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }
    
    
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }   

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    } 

    /**
     * Add a new idea to the database
     *
     * @return 
     */
    public String create() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Person currentUser = (Person)externalContext.getSessionMap().get("user");
        
        Idea newIdea = new Idea();

        newIdea.setTitle(this.title);
        newIdea.setDescription(this.description);
        newIdea.setTags(this.tags);
        newIdea.setOwner(currentUser);
        ideaService.addIdea(newIdea);

        return "/index?faces-redirect=true";
    }  
    
    public String update(){
       // check who it is owned by maybe? 
       ideaService.update(idea);
       return "/idea/view?faces-redirect=true";
    }
    
    public String delete(){
        ideaService.remove(idea);
        return "/index";
    }
                
    public String prepareView(){
        idea = ideaService.getIdea(Long.parseLong(selectedId));
        
        if(idea != null){
        return "/idea/view?faces-redirect=true";
        }
        else{
            return "";
        }
    }
    
    public String prepareEdit(){
      if (idea != null){
          return "/idea/edit?faces-redirect=true";
      }
        return "";
    }
    
    public void retrieve(){
       idea = ideaService.getIdea(Long.parseLong(selectedId));
    }

    public void assignIdea(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Person currentUser = (Person)externalContext.getSessionMap().get("user");
        
        ideaService.assignIdeaToStudent(idea, currentUser);
    } 
}
