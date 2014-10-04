package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by rishi on 5/10/14.
 */

@Entity
public class User extends Model
{
    @Id
    public long id;

    public String userName;

    @Constraints.Email
    public String email;

    public Boolean isAdmin;

    @ManyToOne
    public Organization organization;

    @Transient
    @Constraints.Required
    @JsonIgnore
    public String password;

    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

    @Column( name = "attempts")
    @JsonIgnore
    public byte numberOfAttempts = 0;

    @Lob
    @JsonIgnore
    public byte[] picture;

    @OneToMany( mappedBy = "user", cascade = CascadeType.REMOVE,targetEntity = UserBrowser.class)
    public List<UserBrowser> userBrowsers;


    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPassword(String password) {
        this.password = password;
        shaPassword = getSha512(password);
    }

    public static User findByUsernameAndPassword(String userName, String password)
    {
        return find.where()
                .eq("user_name", userName.toLowerCase())
                    .eq("shaPassword", getSha512(password))
                        .findUnique();
    }

    public static Model.Finder<Long,User> find = new Model.Finder<>(
            Long.class, User.class
    );
}