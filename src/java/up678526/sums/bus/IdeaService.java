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

    public void assignIdeaToStudent(Idea idea, Person student) {
        idea.setStudent(student);
        idea.setAssigned(Boolean.TRUE);
        ideaFacade.edit(idea);
    }

    public void deselectIdea(Idea idea) {
        idea.setAssigned(Boolean.FALSE);
        idea.setStudent(null);
        ideaFacade.edit(idea);
    }
}
