package sample;

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    protected String nid;
    protected String phone;
    protected String email;
    protected String passwords;

    public User(String name, String nid, String phone, String email, String passwords) {
        this.name = name;
        this.nid = nid;
        this.phone = phone;
        this.email = email;
        this.passwords = passwords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
