/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.paramValueType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.IdeaService;
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
        allIdeas = ideaService.getAllIdeas();
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
        newIdea.setPerson(currentUser);
        ideaService.addIdea(newIdea);

        return "/index?faces-redirect=true";
    }  
    
    public String update(){
       // check who it is owned by maybe? 
       ideaService.update(idea);
       return "/idea/view?faces-redirect=true";
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

    public void selectIdea(){
        // check is student
        // add idea to student 
    } 

}
