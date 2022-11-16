package au.edu.unsw.infs3634.unswlearning;

import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String user_id;
    public String name;
    public String username;
    private String email;
    private String password;
    private int countPoint;
    private int countLevel;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Constructor
    public User() {

    }

    public User(String name, String username, String email, String password, int countLevel, int countPoint) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.countLevel = countLevel;
        this.countPoint = countPoint;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference usersRef = ref.child("users");

    //Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCountPoint() {
        return countPoint;
    }

    public void setCountPoint(int countPoint) {
        this.countPoint = countPoint;
    }

    public int getCountLevel() {
        return countLevel;
    }

    public void setCountLevel(int countLevel) {
        this.countLevel = countLevel;
    }

    //Getter methods
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String userID) {
        this.user_id = userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

