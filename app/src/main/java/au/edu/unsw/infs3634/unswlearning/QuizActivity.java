package au.edu.unsw.infs3634.unswlearning;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView quizRegion;
    private TextView countCorrectAnswers;
    private TextView quizLevel;
    private TextView questionID;
    private RadioGroup rbGroup;
    private RadioButton choiceA;
    private RadioButton choiceB;
    private RadioButton choiceC;
    private RadioButton choiceD;
    private Button submitButton;

    //Track questions and answer
    private List<MultipleChoiceQuestion> questionList;
    private Question currentQuestion;
    private int questionCount;
    private int quizScore;
    private boolean answered;

    final int questionCountTotal = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_quiz);

        //Assign design attributes
        quizRegion = findViewById(R.id.quizRegion);
        countCorrectAnswers = findViewById(R.id.countCorrectAnswers);
        quizLevel = findViewById(R.id.quizLevel);
        questionID = findViewById(R.id.questionID);
        rbGroup = findViewById(R.id.rbGroup);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        choiceD = findViewById(R.id.choiceD);
        submitButton = findViewById(R.id.submitButton);

        //questionList = getQuestions();

        //Shuffle the question list
        Collections.shuffle(questionList);
        showNextQuestion();


    }

    private void showNextQuestion() {
        //Unselect choice to start a new question
        rbGroup.clearCheck();

        if (questionCount < questionCountTotal) {

        }
    }

}
