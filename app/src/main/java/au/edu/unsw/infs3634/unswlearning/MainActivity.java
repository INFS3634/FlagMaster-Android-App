package au.edu.unsw.infs3634.unswlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Default page
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new QuizFragment()).commit();

        //Create a button to start quiz
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
                public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectedFragment = null;

            //Check which item is selected
            switch(item.getItemId()) {
                case R.id.learn_page:
                    selectedFragment = new LearnFragment();
                    break;
                case R.id.quiz_page:
                    selectedFragment = new QuizFragment();
                    break;
                case R.id.profile_page:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
            };

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, MCQQuizActivity.class);
        startActivity(intent);
    }
}