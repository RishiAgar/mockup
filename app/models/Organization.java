package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishi on 5/10/14.
 */

@Entity
public class Organization extends Model
{
    @Id
    public long id;

    public String name;

    @OneToMany( mappedBy = "organization", cascade = CascadeType.REMOVE,targetEntity = User.class)
    public List<User> userList;

    @Lob
    @JsonIgnore
    public byte[] logo;

    /*@ManyToMany(mappedBy = "organizations", cascade = CascadeType.ALL)
    public List<Role> roleList = new ArrayList();*/

    public static Model.Finder<Long,Organization> find = new Model.Finder<>(
            Long.class, Organization.class
    );
}
