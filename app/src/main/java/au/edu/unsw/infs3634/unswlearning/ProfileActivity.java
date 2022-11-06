package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    //Edit profile
    private ImageView editButton;
    private TextView editName;
    private TextView editUserName;
    private Button confirmChange;
    //Create a new user (example)
    User user = new User("Paul Ramos", "paul_ramos_01", "Spain", 2, 100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Interface attributes
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        userLocation = findViewById(R.id.userLocation);
        countLevelPassed = findViewById(R.id.countLevelPassed);
        countPoints = findViewById(R.id.countTotalPoints);
        africaBadge = findViewById(R.id.africaBadge);
        asiaBadge = findViewById(R.id.asiaBadge);
        europeBadge = findViewById(R.id.europeBadge);
        northAmericaBadge = findViewById(R.id.northAmericaBadge);
        southAmericaBadge = findViewById(R.id.southAmericaBadge);
        oceaniaBadge = findViewById(R.id.oceaniaBadge);

        //Bottom Navigation View
        bottomNav = findViewById(R.id.bottomNavigationView);

        //Edit button
        editButton = findViewById(R.id.editButton);
        editName = findViewById(R.id.editName);
        editUserName = findViewById(R.id.editUserName);
        confirmChange = findViewById(R.id.confirmChange);

        //Display user profile
        name.setText(user.getName());
        username.setText(user.getUsername());
        userLocation.setText(user.getLocation());
        //countLevelPassed.setText(user.getCountLevels());
        //countPoints.setText(user.getCountPoints());

        //Set up bottom navigation bar
        bottomNav.setSelectedItemId(R.id.profile);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check which item is selected
                switch (item.getItemId()) {
                    case R.id.quiz:
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.learn:
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        //Edit profile
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButton.setVisibility(View.INVISIBLE);
                editProfile();
            }
        });
        //Set up badges

    }

    public void editProfile() {
        editName.setVisibility(View.VISIBLE);
        name.setVisibility(View.INVISIBLE);
        editUserName.setVisibility(View.VISIBLE);
        name.setVisibility(View.INVISIBLE);
        confirmChange.setVisibility(View.VISIBLE);

        confirmChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.setName((String) editName.getText());
                user.setUsername((String) editUserName.getText());
                confirmChange.setVisibility(View.INVISIBLE);
                editButton.setVisibility(View.VISIBLE);
                editName.setVisibility(View.INVISIBLE);
                name.setVisibility(View.VISIBLE);
                editUserName.setVisibility(View.INVISIBLE);
                name.setVisibility(View.VISIBLE);
            }
        });

    }
}
