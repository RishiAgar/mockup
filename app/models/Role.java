package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishi on 5/10/14.
 */

@Entity
public class Role extends Model
{
    @Id
    public long id;

    @Constraints.Required
    public String name;

    /*@JsonIgnore
    @ManyToMany(mappedBy = "roleList", cascade = CascadeType.ALL)
    public List<Organization> organizations = new ArrayList();*/

    public static Model.Finder<Long,Organization> find = new Model.Finder<>(
            Long.class, Organization.class
    );
}