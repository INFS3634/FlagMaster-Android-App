package au.edu.unsw.infs3634.unswlearning;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShortAnswer extends Question{
        private String userAnswer;
        private ImageView questionImage; //NOT NULL
        private String questionText;

    //Setter methods
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

    public void setQuestionImage(ImageView questionImage) {
        this.questionImage = questionImage;
    }

    public void setQuestionText(String questionText) {this.questionText = questionText; }

    //Getter methods
    public String getUserAnswer(){
        return userAnswer;
    }

    public ImageView getQuestionImage() {
        return questionImage;
    }

    public String getQuestionText() { return questionText; }
}
