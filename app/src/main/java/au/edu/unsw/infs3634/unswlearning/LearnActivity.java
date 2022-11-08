package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LearnActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        bottomNav = findViewById(R.id.bottomNavigationView);
        //Set Quiz selected
        bottomNav.setSelectedItemId(R.id.learn);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check which item is selected
                switch(item.getItemId()) {
                    case R.id.quiz:
                        startActivity(new Intent(LearnActivity.this, QuizActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.learn:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        System.out.println("Heading to Profile Page");
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}