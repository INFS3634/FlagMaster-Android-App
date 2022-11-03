package au.edu.unsw.infs3634.unswlearning;

import static java.util.Arrays.*;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ShortAnswerQuizActivity extends AppCompatActivity {
    private TextView quizRegionTV;
    private TextView quizLevelTV;
    private TextView questionCountTV;
    private TextView quizScoreTV;
    private TextView questionTextTV;
    private ImageView questionImageIV;
    private EditText userAnswerET;
    private Button submitAnswerButton;
    private TextView answerFeedbackTV;

    private List<ShortAnswer> shortAnswerQuestionList;
    final int totalQuestionCount = 5;
    private int questionCount;
    private ShortAnswer currentQuestion;
    private boolean answered;
    private int quizScore;
    private int curQuestion = 0;

    public static String regionChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_answer_quiz);

        // Assign attributes
        quizRegionTV = findViewById(R.id.quizRegionTV);
        quizLevelTV = findViewById(R.id.quizLevelTV);
        quizScoreTV = findViewById(R.id.quizScoreTV);
        questionTextTV = findViewById(R.id.questionTextTV);
        questionImageIV = findViewById(R.id.questionImageIV);
        userAnswerET = findViewById(R.id.userAnswerET);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        answerFeedbackTV = findViewById(R.id.answerFeedbackTV);

        //Take the region name from QuizActivity screen
        Intent intent = getIntent();
        //prevent app from crashing
        //if the intent has been passed through successfully
        if (intent.hasExtra(regionChosen)) {
            //Get message passed from the MainActivity
            String message = intent.getStringExtra(regionChosen);
            //Update text with message
            quizRegionTV.setText(regionChosen);
        }

        shortAnswerQuestionList = new ArrayList<>();

        // Add all questions and answers to the quiz
        try {
            shortAnswerQuestionList = ShortAnswerDatabase.getShortAnswerQuestionByRegion(regionChosen);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Shuffle the questions
        Collections.shuffle(shortAnswerQuestionList);
        showNextQuestion();

        String userAnswer = userAnswerET.getText().toString();

        // Handle submit button
        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (userAnswer.matches("")) {
                        Toast.makeText(ShortAnswerQuizActivity.this, "Please enter your answer", Toast.LENGTH_SHORT).show();
                    } else {
                        checkAnswer();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        // clear user answer to default
        userAnswerET.setText(null);

        if (questionCount < totalQuestionCount) {
            currentQuestion = shortAnswerQuestionList.get(questionCount);
            questionTextTV.setText(currentQuestion.getTextQuestion());

            // Set image to question image
            // questionImageIV.setImage(currentQuestion.getQuestionImage());

            questionCount++;
            questionCountTV.setText("Question: " + questionCount + "/" + totalQuestionCount);
            answered = false;
            submitAnswerButton.setText("SUBMIT");
        }
        else {
            finishQuiz();
        }

    }

    private void checkAnswer() {
        answered = true;

        String userAnswer = userAnswerET.getText().toString();

        // check if the answer is correct
        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            quizScore++;
            quizScoreTV.setText("Score: " + quizScore);
        }
        else {
            showCorrectAnswer();
        }

    }

    private void showCorrectAnswer() {
        String correctAnswer = currentQuestion.getAnswer();
        answerFeedbackTV.setText("The correct answer is " + correctAnswer);
        answerFeedbackTV.setTextColor(Color.RED);

        //Handle submit button
        if (questionCount < questionCount) {
            submitAnswerButton.setText("Next");
        } else {
            submitAnswerButton.setText("FINISH");
        }
    }

    private void finishQuiz() {
        //If user gets 5/5 correct answers, add 10 points to Total Points
        if (quizScore == questionCount) {
            User currentUser = new User();
            currentUser.addPoints();
            currentUser.addLevelPassed();
        }
        finish();
    }
}
