/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import up678526.sums.bus.IdeaService;

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

    private String title;
    private String description;

    @EJB
    private IdeaService ideaService;

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
}
