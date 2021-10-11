package sample;

public class User {
    protected String name;
    protected String nid;
    protected String phone;
    protected String email;
    protected String passwords;
    protected int userId;
    protected static int totalUser = 0;

    public User() {
        this.userId = totalUser;
        totalUser++;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int getTotalUser() {
        return totalUser;
    }

    public static void setTotalUser(int totalUser) {
        User.totalUser = totalUser;
    }
}
