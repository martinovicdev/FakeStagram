package hr.martinovic.FakeStagram.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import hr.martinovic.FakeStagram.utils.CreationPrint;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Users {

    @Id
    @NotEmpty(message = "Please enter username")
    private String username;

    @NotEmpty(message = "Please enter your password")
    private String password;

    private Boolean enabled;

    private Roles permission;

    public Users() {
        this.enabled = true;
    }

    public Users(String username, String password, Roles permission)
    {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        this.permission = permission;
        this.enabled = true;

    }

    public String verifyCreation (){
        return CreationPrint.generateText(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getPermission() {
        return permission;
    }

    public void setPermission(Roles permission) {
        this.permission = permission;
    }

    public enum Roles {
        Basic, Premium
    }

    

}
