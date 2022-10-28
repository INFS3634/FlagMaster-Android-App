package au.edu.unsw.infs3634.unswlearning;

public class User {
    private String name;
    private String username;
    private String location;
    private int countLevels;
    private int countPoints;

    //Constructor
    public User() {

    }

    public User(String name, String username, String registerDate, String location, int countLevels,
                int countPoints) {
        this.name = name;
        this.username = username;
        this.location = location;
        this.countLevels = countLevels;
        this.countPoints = countPoints;
    }

    //Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCountLevels(int countLevels) {
        this.countLevels = countLevels;
    }

    public void setCountPoints(int countPoints) {
        this.countPoints = countPoints;
    }

    //Getter methods
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }


    public String getLocation() {
        return location;
    }

    public int getCountLevels() {
        return countLevels;
    }

    public int getCountPoints() {
        return countPoints;
    }
}

