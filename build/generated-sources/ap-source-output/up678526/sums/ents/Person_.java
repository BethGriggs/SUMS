package up678526.sums.ents;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import up678526.sums.ents.Idea;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-25T16:37:49")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> password;
    public static volatile ListAttribute<Person, Idea> ideas;
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, String> type;
    public static volatile SingularAttribute<Person, String> email;

}