/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import up678526.sums.bus.OrganisationService;
import up678526.sums.ents.Organisation;

/**
 *
 * @author up678526
 */
@Named(value = "organisationBean")
@RequestScoped
public class OrganisationBean implements Serializable {

    private String name;
    private String departmentActivities;
    
    
    @EJB
    private OrganisationService organisationService;
    
    /**
     * Creates a new instance of OrganisationBean
     */
    public OrganisationBean() {
    }

    public List<Organisation> getOrganisations() {
        return organisationService.getOrganisations();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentActivities() {
        return departmentActivities;
    }

    public void setDepartmentActivities(String departmentActivities) {
        this.departmentActivities = departmentActivities;
    }
    
    
    /**
     * Add a new organisation
     *
     * @return 
     */
    public String create() {
 
        Organisation organisation = new Organisation();
        organisation.setName(this.name);
        organisation.setDepartmentActivities(this.departmentActivities);
        organisationService.createOrganisation(organisation);
        return "/person/view?faces-redirect=true";
    }  
}
