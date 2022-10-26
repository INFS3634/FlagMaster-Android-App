package au.edu.unsw.infs3634.unswlearning;

public class MultipleChoiceQuestion extends Question {
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    final int level = 1;

    //Constructor
    public MultipleChoiceQuestion(int id, String region, int level, String textQuestion, String answer,
                                  String choiceA, String choiceB, String choiceC, String choiceD){
        super(id, region, level, textQuestion, answer);
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
    }

    //what about questions that A, B, C, D are images??
    
    //Setter methods
    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    //Getter methods
    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }
}
