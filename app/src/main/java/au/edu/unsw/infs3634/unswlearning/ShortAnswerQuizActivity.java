package au.edu.unsw.infs3634.unswlearning;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ShortAnswerQuizActivity extends AppCompatActivity {
    private TextView quizRegionTV;
    private TextView quizLevelTV;
    private TextView questionCountTV;
    private TextView quizScoreTV;
    private TextView questionTextTV;
    private ImageView questionImageIV;
    private EditText userAnswerET;
    private Button submitAnswerButton;

    private List<ShortAnswer> shortAnswerQuestionList;
    final int totalQuestionCount = 5;
    private int questionCount;
    private ShortAnswer currentQuestion;
    private boolean answered;
    private int quizScore;

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

        try {
            shortAnswerQuestionList = ShortAnswerDatabase.getShortAnswerQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Shuffle the question list
        Collections.shuffle(shortAnswerQuestionList);
        showNextQuestion();
    }

    private void showNextQuestion() {
        // clear user answer to default
        userAnswerET.setText(null);

        if (questionCount < totalQuestionCount) {
            currentQuestion = shortAnswerQuestionList.get(questionCount);
            questionTextTV.setText(currentQuestion.getQuestionText());

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

        // Compare userAnswer with the correct answer
        if (userAnswer == currentQuestion.getUserAnswer()) {
            quizScore++;
            quizScoreTV.setText("Score: " + quizScore);
        }

        showCorrectAnswer();

    }

    private void showCorrectAnswer() {

    }

    private void finishQuiz() {
        finish();
    }
}
