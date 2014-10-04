package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by rishi on 5/10/14.
 */

@Entity
public class UserBrowser extends Model
{
    public long id;

    @ManyToOne
    public User user;

    public String browser;

    public DateTime start;

    public static Model.Finder<Long,UserBrowser> find = new Model.Finder<>(
            Long.class, UserBrowser.class
    );
}
