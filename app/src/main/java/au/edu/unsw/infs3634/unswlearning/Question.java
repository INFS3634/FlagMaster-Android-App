package au.edu.unsw.infs3634.unswlearning;

public abstract class Question {
    private String region;
    private int level;
    private int id;
    private String question;
    private String answer;

    //Setter methods
    public void setRegion(String region) {
        this.region = region;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //Getter methods
    public String getRegion() {
        return region;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
