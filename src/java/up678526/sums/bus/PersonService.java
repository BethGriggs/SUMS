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
import up678526.sums.bus.exception.AuthenticationException;
import up678526.sums.bus.exception.BusinessException;
import up678526.sums.ents.Idea;
import up678526.sums.ents.Organisation;
import up678526.sums.ents.Person;
import up678526.sums.pers.IdeaFacade;
import up678526.sums.pers.PersonFacade;

/**
 * provides user focused functionality including user creation, validation of
 * user credentials. Also, provides functionality for users to select ideas.
 *
 * @author up678526
 */
@Stateless
public class PersonService {

    public PersonService() {
    }

    @EJB
    private PersonFacade personFacade;

    @EJB
    private IdeaFacade ideaFacade;

    /**
     * validates a users email and password
     *
     * @param email
     * @param password
     * @return user if credentials were correct, otherwise null
     * @throws AuthenticationException
     */
    public Person validateCredentials(String email, String password) throws AuthenticationException {
        List<Person> persons = personFacade.findUserByEmail(email);
        // if there is no person matching this email, throw AuthenticationException
        if (persons.isEmpty()) {
            throw new AuthenticationException("Invalid email or password.");
        } else {
            // should only be one person with this email, therefore retrieve first one
            Person person = persons.get(0);
            if (person.getEmail().equals(email) && person.getPassword().equals(password)) {
                return person;
            } else {
                // throw AuthenticationException if email and password do not match
                throw new AuthenticationException("Invalid email or password.");
            }
        }
    }

    /**
     * creates a new user
     *
     * @param person
     * @throws BusinessException
     */
    public void createNewUser(Person person) throws BusinessException {
        if (personFacade.findUserByEmail(person.getEmail()).isEmpty()) {
            personFacade.create(person);
        } else {
            throw new BusinessException("User with this email already exists.");
        }
    }

    /**
     * returns the list of ideas that this staff member has created
     *
     * @param person
     * @return list of ideas owned by the specified user
     */
    public List<Idea> getOwnedIdeas(Person person) {
        return ideaFacade.findIdeasByOwner(person);
    }

    /**
     * assigns a user to an organisation
     *
     * @param person
     * @param organisation
     */
    public void assignOrganisation(Person person, Organisation organisation) {
        person.setOrganisation(organisation);
        personFacade.edit(person);
    }

    /**
     * returns the idea a student has been assigned
     *
     * @param person
     * @return the users assigned idea, otherwise null
     */
    public List<Idea> getAssignedIdea(Person person) {
        return ideaFacade.findUserAssignedIdea(person);
    }
}
