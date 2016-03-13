/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.ctrl;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author up678526
 */
@Named(value = "organisationBean")
@RequestScoped
public class OrganisationBean implements Serializable {

    /**
     * Creates a new instance of OrganisationBean
     */
    public OrganisationBean() {
    }
       
}
