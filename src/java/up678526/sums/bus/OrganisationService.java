/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import up678526.sums.ents.Organisation;
import up678526.sums.pers.OrganisationFacade;

/**
 *
 * @author up678526
 */
@Stateless
public class OrganisationService {

    @EJB
    private OrganisationFacade organisationFacade;
    
    public void createOrganisation(Organisation organisation) {
        organisationFacade.create(organisation);
    }
    
    public List<Organisation> getOrganisations(){
        return organisationFacade.findAll();
    }

}
