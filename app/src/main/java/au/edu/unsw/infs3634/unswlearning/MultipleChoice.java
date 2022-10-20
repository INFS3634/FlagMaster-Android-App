package au.edu.unsw.infs3634.unswlearning;

public class MultipleChoice extends Question {
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    
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
}
