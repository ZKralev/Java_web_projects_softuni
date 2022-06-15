package bg.softuni.pathfinder.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsersPK implements Serializable {

    private long id;
    private String username;

}
