package au.edu.unsw.infs3634.unswlearning;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String name;
    public String username;
    public int count_level;
    public int count_point;

    //Constructor
    public User() {

    }

    public User(String name, String username) {
        this.name = name;
        this.username = name;
    }

    public User(String name, String username, int countLevels, int countPoints) {
        this.name = name;
        this.username = username;
        this.count_level = countLevels;
        this.count_point = countPoints;
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

    public void setCountLevels(int countLevels) {
        this.count_level = countLevels;
    }

    public void setCountPoint(int countPoints) {
        this.count_point = countPoints;
    }

    //Getter methods
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getCountLevel() {
        return count_level;
    }

    public int getCountPoint() {
        return count_point;
    }

    public void addPoints() {
        this.count_point += 10;
    }

    public void addLevelPassed() {
        this.count_level += 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", count_level=" + count_level +
                ", count_point=" + count_point +
                ", database=" + database +
                '}';
    }
}

