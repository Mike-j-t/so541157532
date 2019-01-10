package so54115753.so54115753;

/**
 * My Version of User.java
 * Note foreignid is not really needed
 */
public class User {

    private String name;
    private String email;
    private String password;
    private long foreignId; //??????????
    private long user_Id; //??????

    public User(String name, String email, String password, long userid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_Id = userid;
        this.foreignId = user_Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setForeignID(long foreignId) {
        this.foreignId = foreignId;
        this.user_Id = foreignId;
    }

    public long getForeignID() {
        return foreignId;
    }

    public void setUser_Id(long user_Id) {
        this.user_Id = user_Id;
        this.foreignId = user_Id;
    }

    public long getUser_Id() {
        return user_Id;
    }
}
