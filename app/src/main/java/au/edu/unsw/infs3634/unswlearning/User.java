package au.edu.unsw.infs3634.unswlearning;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String user_id;
    public String name;
    public String username;
    private String email;

    //Constructor
    public User() {

    }

    public User(String user_id, String name, String username, String email) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.email = email;
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

