/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import up678526.sums.bus.IdeaService;
import up678526.sums.ents.Idea;

/**
 *
 * @author up678526
 */
@Named(value = "ideaBean")
@RequestScoped
public class IdeaBean {

    /**
     * Creates a new instance of IdeaBean
     */
    public IdeaBean() {
    }

    private Long id;
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
        return allIdeas;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }   
    
    /**
     * Add a new idea to the database
     *
     * @return 
     */
    public String create() {
        Idea idea = new Idea();

        idea.setTitle(this.title);
        idea.setDescription(description);
        idea.setTags(tags);
        ideaService.addIdea(idea);

        return "/index.xhtml";
    }    
    /** GET: Request context
     *
     * @return external context
     */
    protected ExternalContext request() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    /** Get the URL param
     *
     * @param param value
     * @return long
     */
        protected Long getLongParam(String param) {
        FacesContext.getCurrentInstance().getExternalContext();
        param = request().getRequestParameterMap().get(param);
        if (param != null) {
            return Long.parseLong(param);
        }
        return Long.parseLong("0");
    }

    /**
     * Initialise the controller variables based on a request parameter
     * 
     */
    @PostConstruct
    public void init() {
        allIdeas = ideaService.getAllIdeas();

        id = getLongParam("idea");
        if (id != 0) {
            idea = ideaService.getIdea(id);
            //if (remoteUserIsAuthor()) {
            //this.setEditText("Would you like to edit");
            //}

            int i = 1;
        }
        
    } 
}
