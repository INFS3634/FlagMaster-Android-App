package au.edu.unsw.infs3634.unswlearning;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShortAnswer extends Question{
        
    private String userAnswer;
    private ImageView questionImage; //NOT NULL
//Constructor
    public ShortAnswer(int id, String region, String textQuestion, String answer,
                       String userAnswer, ImageView questionImage) {
        super(id, region, textQuestion, answer);
        this.userAnswer = userAnswer;
        this.questionImage = questionImage;
    }
    //Setter methods
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

    public void setQuestionImage(ImageView questionImage) {
        this.questionImage = questionImage;
    }

    //Getter methods
    public String getUserAnswer(){
        return userAnswer;
    }

    public ImageView getQuestionImage() {
        return questionImage;
    }

}
