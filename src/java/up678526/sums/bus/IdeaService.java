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
import up678526.sums.ents.Person;
import up678526.sums.pers.IdeaFacade;

/**
 *
 * @author up678526
 */
@Stateless
public class IdeaService {

    public IdeaService() {
    }

        // more named towards what the business operation 'create', 'createIdea' 
         // is this okay to do - business logic ... Bank account not overdrawn 
    // mutiple facades between entities 
    @EJB
    private IdeaFacade ideaFacade;

    public Idea getIdea(Long id) {
        return ideaFacade.find(id);
    }

    public void addIdea(Idea idea) {
        idea.setAssigned(Boolean.FALSE);
        ideaFacade.create(idea);
    }
 
    public List<Idea> getAllAvailableIdeas() {
        return ideaFacade.findAllUnassignedIdeas();
    }

    public Idea update(Idea idea) {
        return ideaFacade.edit(idea);
    }

    public void remove(Idea idea) {
        ideaFacade.remove(idea);
    }
    
    public void assignIdeaToStudent(Idea idea, Person student){
        idea.setStudent(student);
        idea.setAssigned(Boolean.TRUE);
        ideaFacade.edit(idea);
    }
    
    public void deselectIdea(Idea idea){
       idea.setAssigned(Boolean.FALSE);
       idea.setStudent(null);
       ideaFacade.edit(idea);
    }
}
