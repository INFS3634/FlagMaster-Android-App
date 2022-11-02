package au.edu.unsw.infs3634.unswlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.short_answer_quiz);

        //Create a button to start quiz
    }

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, MCQQuizActivity.class);
        startActivity(intent);
    }


    /*
    BottomNavigationView.OnNavigationItemSelectedListener {item ->
            when(item.itemId) {
        R.id.item1 -> {
            // Respond to navigation item 1 click
            true
        }
        R.id.item2 -> {
            // Respond to navigation item 2 click
            true
        }
        else -> false
    }
    }

     */
}