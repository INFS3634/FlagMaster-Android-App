package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        bottomNav = findViewById(R.id.bottomNavigationView);

        asiaMap = findViewById(R.id.asiaMap);
        africaMap = findViewById(R.id.africaMap);
        europeMap = findViewById(R.id.europeMap);
        northAmericaMap = findViewById(R.id.northAmericaMap);
        southAmericaMap = findViewById(R.id.southAmericaMap);
        oceaniaMap = findViewById(R.id.oceaniaMap);

        //Set Quiz selected
        bottomNav.setSelectedItemId(R.id.quiz_page);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
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
            });


        //https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/



        chooseRegion();
    }


    //Push regionChosen from region map button to MCQQuizActivity
    public void launchMCQQuizActivity(String regionChosen) {
        //refer when switching screen
        Intent intent = new Intent(QuizActivity.this, MCQQuizActivity.class); //refer to the current activity in main
        //transport message from MainActivity to DetailActivity
        intent.putExtra(MCQQuizActivity.regionChosen, regionChosen);
        //start new activity
        startActivity(intent); //call variable intent
    }

    public void chooseRegion() {
        //Choose Asia
        asiaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("Asia");
            }
        });
        //Choose Asia
        africaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("Africa");
            }
        });
        //Choose Asia
        europeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("Europe");
            }
        });
        //Choose Asia
        northAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("North America");
            }
        });
        //Choose Asia
        southAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("South America");
            }
        });
        //Choose Asia
        oceaniaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMCQQuizActivity("Oceania");
            }
        });
    }

}
