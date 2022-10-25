package au.edu.unsw.infs3634.unswlearning;

public class Question {
    private int id;
    private String region;
    private int level;
    private String textQuestion;
    private String answer;

    //Constructor
    public Question(){

    }

    public Question(int id, String region, int level, String textQuestion, String answer) {
        this.id = id;
        this.region = region;
        this.level = level;
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

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

    public void setTextQuestion(String question) {
        this.textQuestion = question;
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
        return textQuestion;
    }

    public String getAnswer() {
        return answer;
    }
}
