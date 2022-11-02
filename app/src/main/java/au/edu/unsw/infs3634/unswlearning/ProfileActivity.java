package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    private TextView name;
    private TextView username;
    private TextView userLocation;
    private TextView countLevelPassed;
    private TextView countPoints;
    private ImageView africaBadge;
    private ImageView asiaBadge;
    private ImageView europeBadge;
    private ImageView northAmericaBadge;
    private ImageView southAmericaBadge;
    private ImageView oceaniaBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Interface attributes
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        userLocation = findViewById(R.id.userLocation);
        countLevelPassed = findViewById(R.id.countLevelPassed);
        countPoints = findViewById(R.id.countPoints);
        africaBadge = findViewById(R.id.africaBadge);
        asiaBadge = findViewById(R.id.asiaBadge);
        europeBadge = findViewById(R.id.europeBadge);
        northAmericaBadge = findViewById(R.id.northAmericaBadge);
        southAmericaBadge = findViewById(R.id.southAmericaBadge);
        oceaniaBadge = findViewById(R.id.oceaniaBadge);
        //Bottom Navigation View
        bottomNav = findViewById(R.id.bottomNavigationView);

        //Create a new user (example)
        User user = new User("Paul Ramos", "paul_ramos_01", "Spain", 2, 100);

        //Display user profile
        name.setText(user.getName());
        username.setText(user.getUsername());
        userLocation.setText(user.getLocation());
        countLevelPassed.setText(user.getCountLevels() + " Levels Passed");
        countPoints.setText(user.getCountPoints() + " Points Earned");

        //Set up bottom navigation bar
        bottomNav.setSelectedItemId(R.id.profile_page);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check which item is selected
                switch (item.getItemId()) {
                    case R.id.quiz_page:
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.learn_page:
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile_page:
                        return true;
                }
                return false;
            }
        });

        //Set up badges

    }
}
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_quiz, container, false);

    }*/
