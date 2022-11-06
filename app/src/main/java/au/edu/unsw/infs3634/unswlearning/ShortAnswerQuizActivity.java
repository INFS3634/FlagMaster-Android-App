package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Collections;
import java.util.List;

public class ShortAnswerQuizActivity extends AppCompatActivity {
    private TextView quizRegionTV;
    private TextView questionCountTV;
    private TextView quizScoreTV;
    private TextView textQuestionTV;
    private ImageView questionImageIV;
    private EditText userAnswerET;
    private Button submitAnswerButton;
    private TextView checkAnswer;

    private List<ShortAnswer> shortAnswerQuestionListByRegion;
    final int totalQuestionCount = 5;
    private int questionCount;
    private ShortAnswer currentQuestion;
    private boolean answered;
    private int quizScore;

    public static String regionChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_answer_quiz);

        // Assign attributes
        quizRegionTV = findViewById(R.id.quizRegionTV);
        quizScoreTV = findViewById(R.id.quizScoreTV);
        textQuestionTV = findViewById(R.id.textQuestionTV);
        questionImageIV = findViewById(R.id.questionImageIV);
        userAnswerET = findViewById(R.id.userAnswerET);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        checkAnswer = findViewById(R.id.checkAnswer);

        //Take the region name from QuizActivity screen
        Intent intent = getIntent();
        //prevent app from crashing
        //if the intent has been passed through successfully
        if (intent.hasExtra(regionChosen)) {
            //Get message passed from the MainActivity
            regionChosen = intent.getStringExtra(regionChosen);
            //Update text with message
            quizRegionTV.setText(regionChosen);
        }

        //Create ShortAnswer Database
        ShortAnswerDatabase database = Room.databaseBuilder(getApplicationContext(),
                ShortAnswerDatabase.class, "Short_Answer_Database")
                .allowMainThreadQueries().build();
        ShortAnswerDao shortAnswerDao = database.shortAnswerDao();

        //Insert data to database
        shortAnswerDao.insert(new ShortAnswer(1, "Asia","Iran", "What country is this?", "Iran"));
        shortAnswerDao.insert(new ShortAnswer(2, "Asia","Japan", "What is the capital of this country?", "Tokyo"));
        shortAnswerDao.insert(new ShortAnswer(3, "Asia","India", "What country is this?", "India"));
        shortAnswerDao.insert(new ShortAnswer(4, "Asia","Vietnam", "What is the capital of this country?", "Hanoi"));
        shortAnswerDao.insert(new ShortAnswer(5, "Asia","Pakistan", "What country is this?", "Pakistan"));
        shortAnswerDao.insert(new ShortAnswer(6, "Europe","France", "What country is this?", "France"));
        shortAnswerDao.insert(new ShortAnswer(7, "Europe","Netherlands", "What is the capital of this country?", "Amsterdam"));
        shortAnswerDao.insert(new ShortAnswer(8, "Europe","Denmark", "What country is this?", "Denmark"));
        shortAnswerDao.insert(new ShortAnswer(9, "Europe","Greece", "What is the capital of this country?", "Athens"));
        shortAnswerDao.insert(new ShortAnswer(10, "Europe","Luxembourg", "What country is this?", "Luxembourg"));
        shortAnswerDao.insert(new ShortAnswer(11, "North America","Mexico", "What country is this?", "Mexico"));
        shortAnswerDao.insert(new ShortAnswer(12, "North America","Canada", "What is the capital of this country?", "Ottawa"));
        shortAnswerDao.insert(new ShortAnswer(13, "North America","Cuba", "What country is this?", "Cuba"));
        shortAnswerDao.insert(new ShortAnswer(14, "North America","Jamaica", "What is the capital of this country?", "Kingston"));
        shortAnswerDao.insert(new ShortAnswer(15, "North America","The Bahamas", "What country is this?", "The Bahamas"));
        shortAnswerDao.insert(new ShortAnswer(16, "South America","Colombia", "What country is this?", "Colombia"));
        shortAnswerDao.insert(new ShortAnswer(17, "South America","Brazil", "What is the capital of this country?", "Brasilia"));
        shortAnswerDao.insert(new ShortAnswer(18, "South America","Venezuela", "What country is this?", "Venezuela"));
        shortAnswerDao.insert(new ShortAnswer(19, "South America","Ecuador", "What is the capital of this country?", "Quito"));
        shortAnswerDao.insert(new ShortAnswer(20, "South America","Argentina", "What country is this?", "Argentina"));
        shortAnswerDao.insert(new ShortAnswer(21, "Oceania","Fiji", "What country is this?", "Fiji"));
        shortAnswerDao.insert(new ShortAnswer(22, "Oceania","Fiji", "What is the capital of this country?", "Suva"));
        shortAnswerDao.insert(new ShortAnswer(23, "Oceania","Solomon Islands", "What country is this?", "Solomon Islands"));
        shortAnswerDao.insert(new ShortAnswer(24, "Oceania","Marshall Islands", "What country is this?", "Marshall Islands"));
        shortAnswerDao.insert(new ShortAnswer(25, "Oceania","Vanuatu", "What is the capital of this country?", "Port Vila"));
        shortAnswerDao.insert(new ShortAnswer(26, "Africa","South Africa", "What country is this?", "South Africa"));
        shortAnswerDao.insert(new ShortAnswer(27, "Africa","Nigeria", "What is the capital of this country?", "Lagos"));
        shortAnswerDao.insert(new ShortAnswer(28, "Africa","Zimbabwe", "What country is this?", "Zimbabwe"));
        shortAnswerDao.insert(new ShortAnswer(29, "Africa","Egypt", "What is the capital of this country?", "Cairo"));
        shortAnswerDao.insert(new ShortAnswer(30, "Africa","Botswana", "What country is this?", "Botswana"));

        //Get questions by region
        shortAnswerQuestionListByRegion = shortAnswerDao.getQuestionByRegion(regionChosen);
        //Shuffle the question list
        Collections.shuffle(shortAnswerQuestionListByRegion);
        showNextQuestion();
    }

    private void showNextQuestion() {
        // clear user answer to default
        userAnswerET.setText(null);

        if (questionCount < totalQuestionCount) {
            currentQuestion = shortAnswerQuestionListByRegion.get(questionCount);
            textQuestionTV.setText(currentQuestion.getTextQuestion());

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

        //Compare userAnswer with the correct answer
        if (userAnswer == currentQuestion.getAnswer()) {
            quizScore++;
            quizScoreTV.setText("Score: " + quizScore);
            checkAnswer.setVisibility(View.VISIBLE);
            checkAnswer.setText("Nicely done!");
        } else {
            checkAnswer.setText("Correct answer is: " + currentQuestion.getAnswer());
            checkAnswer.setTextColor(Color.RED);
            checkAnswer.setVisibility(View.VISIBLE);
        }
    }

    private void finishQuiz() {
        if (quizScore == totalQuestionCount) {
            User currentUser = new User();
            currentUser.addPoints();
            currentUser.addLevelPassed();
        }
        finish();
    }
}
