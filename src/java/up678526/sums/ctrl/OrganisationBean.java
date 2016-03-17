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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import up678526.sums.bus.OrganisationService;
import up678526.sums.ents.Organisation;

/**
 * Controller for creating and assigning organisations 
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

    /**
     *
     * @return list of organisations
     */
    public List<Organisation> getOrganisations() {
        return organisationService.getOrganisations();
    }
        
    /**
     * Add a new organisation
     *
     * @return person/view page
     */
    public String create() {
 
        Organisation organisation = new Organisation();
        organisation.setName(this.name);
        organisation.setDepartmentActivities(this.departmentActivities);
        organisationService.createOrganisation(organisation);
        return "/person/view?faces-redirect=true";
    } 
    
    /* getters and setters */ 

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return departmentActivities
     */
    public String getDepartmentActivities() {
        return departmentActivities;
    }

    /**
     *
     * @param departmentActivities
     */
    public void setDepartmentActivities(String departmentActivities) {
        this.departmentActivities = departmentActivities;
    } 
}
