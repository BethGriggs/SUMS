/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import up678526.sums.ents.Idea;
import up678526.sums.pers.IdeaFacade;

/**
 *
 * @author up678526
 */
@Stateless
public class IdeaService {

    public IdeaService() {
    }

    @EJB
    private IdeaFacade personFacade;

    protected IdeaFacade getFacade() {
        return this.personFacade;
    }

    public Idea find(Long id) {
        return getFacade().find(id);
    }

    public void create(Idea idea) {
        getFacade().create(idea);
    }

    public List<Idea> findAll() {
        return getFacade().findAll();
    }

    public Idea update(Idea idea) {
        return getFacade().edit(idea);
    }

    public void remove(Idea idea) {
        getFacade().remove(idea);
    }
}
