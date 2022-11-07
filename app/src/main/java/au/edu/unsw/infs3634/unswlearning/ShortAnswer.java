package au.edu.unsw.infs3634.unswlearning;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShortAnswer extends Question{

    private String userAnswer;
    private String questionText;
    private ImageView questionImage; //NOT NULL

    //Constructor
    public ShortAnswer(int id, String region, String textQuestion, String answer) {
        super(id, region, textQuestion, answer);
        this.userAnswer = userAnswer;
        this.questionText = questionText;
        this.questionImage = questionImage;
    }

    public ShortAnswer(int anInt, String rsString1, String string1, String s, String rsString, String string, String userAnswer, String questionText) {

    }

    //Setter methods
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public void setQuestionImage(ImageView questionImage) {
        this.questionImage = questionImage;
    }

    //Getter methods
    public String getUserAnswer(){
        return userAnswer;
    }

    public String getQuestionText(){
        return questionText;
    }

    public ImageView getQuestionImage() {
        return questionImage;
    }

}
