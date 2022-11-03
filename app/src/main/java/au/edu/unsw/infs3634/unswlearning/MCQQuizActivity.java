package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class MCQQuizActivity extends AppCompatActivity {

    public MCQQuizActivity() {

    }

    private TextView quizRegion;
    private TextView textViewQuestion;
    private TextView textViewQuizScore;
    private TextView textViewQuizLevel;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton choiceA;
    private RadioButton choiceB;
    private RadioButton choiceC;
    private RadioButton choiceD;
    private Button submitButton;

    //Color of text
    private ColorStateList textColorDefaultRb;

    //Track questions and answer
    private ArrayList<MultipleChoiceQuestion> questionListByRegion;
    final int questionCountTotal = 5;
    private MultipleChoiceQuestion currentQuestion;
    private int questionCount;
    private int quizScore;
    private boolean answered;

    //Region question banks chosen
    public static String regionChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_quiz);

        //Assign design attributes
        quizRegion = findViewById(R.id.quizRegion);
        textViewQuestion = findViewById(R.id.textQuestion);
        textViewQuizScore = findViewById(R.id.quizScore);
        textViewQuestionCount = findViewById(R.id.questionCount);
        rbGroup = findViewById(R.id.rbGroup);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        choiceD = findViewById(R.id.choiceD);
        submitButton = findViewById(R.id.submitButton);

        //Color
        textColorDefaultRb = choiceA.getTextColors();

        //Take the region name from QuizActivity screen
        Intent intent = getIntent();
        //prevent app from crashing
        //if the intent has been passed through successfully
        if (intent.hasExtra(regionChosen)) {
            //Get message passed from the MainActivity
            String message = intent.getStringExtra(regionChosen);
            //Update text with message
            quizRegion.setText(regionChosen);
        }

        //Get question bank
        try {
            //Get set of questions based on user's choice of region
            ArrayList<MultipleChoiceQuestion> questionListByRegion = MCQDatabase.getQuestionsByRegion(regionChosen);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Shuffle the question list
        Collections.shuffle(questionListByRegion);
        showNextQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the answer is not answered yet, check the answer
                if (!answered) {
                    if (choiceA.isChecked() || choiceB.isChecked() || choiceC.isChecked() ||
                            choiceD.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(MCQQuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion() {
        //Set text color of options back to default color
        choiceA.setTextColor(textColorDefaultRb);
        choiceB.setTextColor(textColorDefaultRb);
        choiceC.setTextColor(textColorDefaultRb);
        choiceD.setTextColor(textColorDefaultRb);

        //Unselect choice to start a new question
        rbGroup.clearCheck();

        //only show next question when having questions left
        if (questionCount < questionCountTotal) {
            currentQuestion = questionListByRegion.get(questionCount);
            textViewQuestion.setText(currentQuestion.getTextQuestion());
            choiceA.setText(currentQuestion.getChoiceA());
            choiceB.setText(currentQuestion.getChoiceB());
            choiceC.setText(currentQuestion.getChoiceC());
            choiceD.setText(currentQuestion.getChoiceD());

            questionCount++;
            textViewQuestionCount.setText("Question: " + questionCount + "/" + questionCountTotal);
            answered = false;
            submitButton.setText("SUBMIT");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        String userChoice = rbSelected.getText().toString();

        //Compare userChoice with the correct answer
        if (userChoice == currentQuestion.getAnswer()) {
            quizScore++;
            textViewQuizScore.setText("Score: " + quizScore);
        }
        showSolution();

    }

    private void showSolution() {
        //Set text color of all options to red, then change text color of correct option to green
        choiceA.setTextColor(Color.RED);
        choiceB.setTextColor(Color.RED);
        choiceC.setTextColor(Color.RED);
        choiceD.setTextColor(Color.RED);

        String correctAnswer = currentQuestion.getAnswer();
        String option1 = choiceA.getText().toString();
        String option2 = choiceB.getText().toString();
        String option3 = choiceC.getText().toString();
        String option4 = choiceC.getText().toString();

        if (correctAnswer == option1) {
            choiceA.setTextColor(Color.GREEN);
        } else if (correctAnswer == option2) {
            choiceB.setTextColor(Color.GREEN);
        } else if (correctAnswer == option3) {
            choiceC.setTextColor(Color.GREEN);
        } else {
            choiceD.setTextColor(Color.GREEN);
        }

        if (questionCount < questionCountTotal) {
            submitButton.setText("Next");
        } else {
            submitButton.setText("FINISH");
        }
    }

    private void finishQuiz() {
        //If user gets 5/5 correct answers, add 10 points to Total Points
        if (quizScore == questionCountTotal) {
            User currentUser = new User();
            currentUser.addPoints();
            currentUser.addLevelPassed();
        }
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
