package up678526.sums.ents;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import up678526.sums.ents.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-18T09:39:04")
@StaticMetamodel(Idea.class)
public class Idea_ { 

    public static volatile SingularAttribute<Idea, Person> owner;
    public static volatile SingularAttribute<Idea, Person> student;
    public static volatile SingularAttribute<Idea, String> description;
    public static volatile SingularAttribute<Idea, Boolean> assigned;
    public static volatile SingularAttribute<Idea, Long> id;
    public static volatile SingularAttribute<Idea, String> title;
    public static volatile SingularAttribute<Idea, String> tags;

}