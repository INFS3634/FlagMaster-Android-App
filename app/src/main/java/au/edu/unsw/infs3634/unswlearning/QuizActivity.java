package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//SecondFragment for Quiz page
public class QuizActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    //Region maps
    private ImageView asiaMap;
    private ImageView africaMap;
    private ImageView europeMap;
    private ImageView northAmericaMap;
    private ImageView southAmericaMap;
    private ImageView oceaniaMap;

    //Selection of Quiz
    private String regionChosen;
    private String quizLevel;

    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //bottomNav = findViewById(R.id.bottomNavigationView);

        asiaMap = findViewById(R.id.asiaMap);
        africaMap = findViewById(R.id.africaMap);
        europeMap = findViewById(R.id.europeMap);
        northAmericaMap = findViewById(R.id.northAmericaMap);
        southAmericaMap = findViewById(R.id.southAmericaMap);
        oceaniaMap = findViewById(R.id.oceaniaMap);

        //Set Quiz selected
        bottomNav.setSelectedItemId(R.id.quiz_page);

        //Perform item selected listener
        /*bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check which item is selected
                switch(item.getItemId()) {
                    case R.id.quiz_page:
                        return true;
                    case R.id.learn_page:
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile_page:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
            });*/

        //Choose Region to learn
        chooseRegion();
    }

    public void chooseRegion() {
        //Choose Asia
        asiaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Asia";
                //startActivity(new Intent(QuizActivity.this, Popup.class));
                Popup popup = new Popup();
                popup.showPopUpWindow();
                //launchMCQQuizActivity("Asia");
            }
        });
        //Choose Asia
        africaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Africa";
                Popup popup = new Popup();
                popup.showPopUpWindow();
                //launchMCQQuizActivity("Africa");
            }
        });
        //Choose Asia
        europeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Europe";
                Popup popup = new Popup();
                popup.showPopUpWindow();
                //launchMCQQuizActivity("Europe");
            }
        });
        //Choose Asia
        northAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "North America";
                Popup popup = new Popup();
                popup.showPopUpWindow();
                launchMCQQuizActivity("North America");
            }
        });
        //Choose South America
        southAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "South America";
                Popup popup = new Popup();
                popup.showPopUpWindow();
                launchMCQQuizActivity("South America");
            }
        });
        //Choose Oceania
        oceaniaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Oceania";
                Popup popup = new Popup();
                popup.showPopUpWindow();
                chooseQuizLevel(popup);
                //launchMCQQuizActivity("Oceania");
            }
        });

        //Push regionChosen from region map button to MCQQuizActivity

    }

    public void chooseQuizLevel(Popup popup) {
        popup.easyQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchMCQQuizActivity(regionChosen);
                }
            });

        popup.mediumQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchShortAnswerActivity(regionChosen);
                }
            });
        }
    public void launchMCQQuizActivity (String regionChosen){
        //refer when switching screen
        Intent intent = new Intent(QuizActivity.this, MCQQuizActivity.class); //refer to the current activity in main
        //transport message from MainActivity to DetailActivity
        intent.putExtra(MCQQuizActivity.regionChosen, regionChosen);
        //start new activity
        startActivity(intent); //call variable intent
    }

    public void launchShortAnswerActivity(String regionChosen){
        //refer when switching screen
        Intent intent = new Intent(QuizActivity.this, ShortAnswerQuizActivity.class); //refer to the current activity in main
        //transport message from MainActivity to DetailActivity
        intent.putExtra(ShortAnswerQuizActivity.regionChosen, regionChosen);
        //start new activity
        startActivity(intent); //call variable intent
        }





}
