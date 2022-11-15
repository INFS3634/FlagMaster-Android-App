package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class MCQQuizActivity extends AppCompatActivity{

    private TextView quizRegion;
    private TextView textViewQuizScore;
    private TextView textViewQuestionCount;
    private ImageView questionImage;
    private RadioGroup rbGroup;
    private RadioButton choiceA;
    private RadioButton choiceB;
    private RadioButton choiceC;
    private RadioButton choiceD;
    private Button submitButton;

    //Color of text
    private ColorStateList textColorDefaultRb;

    //Track questions and answer
    List<MCQ> mcqListByRegion;

    final int questionCountTotal = 5;
    private MCQ currentQuestion;
    private int questionCount;
    private int quizScore;
    private boolean answered;

    //Region question banks chosen
    public static String regionChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_quiz);

        //Assign attributes
        quizRegion = findViewById(R.id.quizRegion);
        textViewQuizScore = findViewById(R.id.quizScore);
        textViewQuestionCount = findViewById(R.id.questionCount);
        rbGroup = findViewById(R.id.rbGroup);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        choiceD = findViewById(R.id.choiceD);
        questionImage = findViewById(R.id.questionImage);
        submitButton = findViewById(R.id.submitButton);

        //Color
        textColorDefaultRb = choiceA.getTextColors();

        //Take the region name from QuizActivity screen
        Intent intent = getIntent();
        //prevent app from crashing
        //if the intent has been passed through successfully
        if (intent.hasExtra(regionChosen)) {
            //Get message passed from the MainActivity
            regionChosen = intent.getStringExtra(regionChosen);
            //Update text with message
            quizRegion.setText(regionChosen);
        }

        //Create MCQ Database
        MCQDatabase database = Room.databaseBuilder(getApplicationContext(),
                MCQDatabase.class, "MCQ_Database").allowMainThreadQueries().build();
        MCQDao mcqDao = database.mainDao();

        mcqDao.insert(new MCQ(1,"South America", "Brazil", "Tanzania","Nigeria","Rwanda","Brazil"));
        mcqDao.insert(new MCQ(2,"South America", "Paraguay", "Suriname","Peru","Chile","Chile"));
        mcqDao.insert(new MCQ(3,"South America", "Argentina", "Uruguay","Escuador","Paraguay","Uruguay"));
        mcqDao.insert(new MCQ(4,"South America", "Venezuela", "Escuador","Paraguay","Columbia","Columbia"));
        mcqDao.insert(new MCQ(5,"South America", "Peru", "Brazil","Venezuela","Argentina","Venezuela"));
        mcqDao.insert(new MCQ(6,"Africa", "South Africa", "Zimbabwe","Angola","Tanzania","South Africa"));
        mcqDao.insert(new MCQ(7,"Africa", "Mauritius", "Botswana","Tanzania","Rwanda","Botswana"));
        mcqDao.insert(new MCQ(8,"Africa", "Nigeria", "South Africa","Zimbabwe","Ivory Coast","Nigeria"));
        mcqDao.insert(new MCQ(9,"Africa", "Botswana", "Ivory Coast","Angola","Egypt","Egypt"));
        mcqDao.insert(new MCQ(10,"Africa", "Ivory Coast", "Mauritius","Rwanada","Tanzania","Mauritius"));
        mcqDao.insert(new MCQ(11,"Oceania", "Palau", "Fiji","Tuvalu","Solomon Islands","Tuvalu"));
        mcqDao.insert(new MCQ(12,"Oceania", "Nauru", "Vanatu","Tonga","Marshall Islands","Nauru"));
        mcqDao.insert(new MCQ(13,"Oceania", "Papua New Guinea", "Tuvalu","Samoa","Fiji","Papua New Guinea"));
        mcqDao.insert(new MCQ(14,"Oceania", "Tonga", "Solomon Islands","Palau","Palau","Solomon Islands"));
        mcqDao.insert(new MCQ(15,"Oceania", "Vanatu", "Samoa","Marshall Islands","Tonga","Samoa"));
        mcqDao.insert(new MCQ(16,"Europe", "Poland", "Luxembourg","Denmark","Netherlands","Denmark"));
        mcqDao.insert(new MCQ(17,"Europe", "Netherlands", "France","Italy","Romania","France"));
        mcqDao.insert(new MCQ(18,"Europe", "Sweden", "Germany","Luxembourg","Poland","Sweden"));
        mcqDao.insert(new MCQ(19,"Europe", "UK", "Greece","Romania","Italy","UK"));
        mcqDao.insert(new MCQ(20,"Europe", "Greece", "Poland","Netherlands","Denmark","Netherlands"));
        mcqDao.insert(new MCQ(21,"Asia", "Iran", "Pakistan","Myanmar","Vietnam","Vietnam"));
        mcqDao.insert(new MCQ(22,"Asia", "Philippines", "Japan","South Korea","Uzbekistan","Japan"));
        mcqDao.insert(new MCQ(23,"Asia", "Vietnam", "India","Singapore","Iran","Iran"));
        mcqDao.insert(new MCQ(24,"Asia", "Japan", "Myanmar","Pakistan","Phillippines","Pakistan"));
        mcqDao.insert(new MCQ(25,"Asia", "South Korea", "Japan","Iran","Uzbekistan","South Korea"));
        mcqDao.insert(new MCQ(26,"North America", "Puerto Ricco", "Panama","United States","Canada","United States"));
        mcqDao.insert(new MCQ(27,"North America", "Costa Rica", "Cuba","Panama","Jamaica","Cuba"));
        mcqDao.insert(new MCQ(28,"North America", "Jamaica", "Mexico","Puerto Rico","The Bahamas","The Bahamas"));
        mcqDao.insert(new MCQ(29,"North America", "Panama", "Honduras","Canada","Cuba","Honduras"));
        mcqDao.insert(new MCQ(30,"North America", "The Bahamas", "Puerto Rico","Mexico","Costa Rica","Costa Rica"));

        //Get questions by region
        mcqListByRegion = mcqDao.getQuestionByRegion(regionChosen);

        //Shuffle the question list
        Collections.shuffle(mcqListByRegion);
        showNextQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the answer is answered, check the answer
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
            currentQuestion = mcqListByRegion.get(questionCount);
            choiceA.setText(currentQuestion.getChoiceA());
            choiceB.setText(currentQuestion.getChoiceB());
            choiceC.setText(currentQuestion.getChoiceC());
            choiceD.setText(currentQuestion.getChoiceD());

            questionCount++;
            textViewQuestionCount.setText("Question: " + questionCount + "/" + questionCountTotal);
            //Add Glide library to display country flag images
            Glide.with(getApplicationContext())
                    .load("https://countryflagsapi.com/png/" + currentQuestion.getAnswer() + "/")
                    .fitCenter()
                    .into(questionImage);
            Log.d(TAG, "showImageQuestion url: " + currentQuestion.getAnswer());

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
        if (userChoice.equalsIgnoreCase(currentQuestion.getAnswer())) {
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
        /*String option1 = choiceA.getText().toString();
        String option2 = choiceB.getText().toString();
        String option3 = choiceC.getText().toString();
        String option4 = choiceC.getText().toString();*/

        if (correctAnswer.equals(currentQuestion.getChoiceA())) {
            choiceA.setTextColor(Color.GREEN);
        } else if (correctAnswer.equals(currentQuestion.getChoiceB())) {
            choiceB.setTextColor(Color.GREEN);
        } else if (correctAnswer.equals(currentQuestion.getChoiceC())) {
            choiceC.setTextColor(Color.GREEN);
        } else if (correctAnswer.equals(currentQuestion.getChoiceD())){
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
            /*currentUser.addPoints();
            currentUser.addLevelPassed();*/
        }
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
