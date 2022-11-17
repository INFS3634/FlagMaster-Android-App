package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//SecondFragment for Quiz page
public class QuizActivity extends AppCompatActivity {

    //Region maps
    private ImageView asiaMap;
    private ImageView africaMap;
    private ImageView europeMap;
    private ImageView northAmericaMap;
    private ImageView southAmericaMap;
    private ImageView oceaniaMap;

    //Selection of Quiz
    private String regionChosen;
    //private String quizLevel;

    Intent intent = getIntent();

    //Popup Window
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private ImageView easyQuiz, hardQuiz;
    private TextView regionChosenTv;
    public static TextView countPoint, countStar;
    //Firebase Database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        asiaMap = findViewById(R.id.asiaMap);
        africaMap = findViewById(R.id.africaMap);
        europeMap = findViewById(R.id.europeMap);
        northAmericaMap = findViewById(R.id.northAmericaMap);
        southAmericaMap = findViewById(R.id.southAmericaMap);
        oceaniaMap = findViewById(R.id.oceaniaMap);
        countPoint = findViewById(R.id.countPoint);
        countStar = findViewById(R.id.countStar);

        //Set up Firebase Database
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");

        //Display user data
        databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieve user data from database
                //User result = (User) dataSnapshot.getValue();
                User result = dataSnapshot.getValue(User.class);

                if (result != null) {
                    Log.d(TAG, "retrieveDataFromDB");
                    countStar.setText(String.valueOf(result.getCountLevel()));
                    countPoint.setText(String.valueOf(result.getCountPoint()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("databaseReference failed!");
            }
        });
        //Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.quiz);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.quiz:
                        return true;
                    case R.id.learn:
                        startActivity(new Intent(QuizActivity.this, LearnActivity.class));
                        System.out.println("Learn");
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        System.out.println("Profile");
                        startActivity(new Intent(QuizActivity.this, ProfileActivity.class));
                        return true;
                }
                return false;
            }
            });

        //Choose Region to learn
        chooseRegion();

    }

    public void createPopupWindow() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
        easyQuiz = popupView.findViewById(R.id.easyQuiz);
        hardQuiz = popupView.findViewById(R.id.hardQuiz);
        regionChosenTv = popupView.findViewById(R.id.regionChosen);
        regionChosenTv.setText(regionChosen);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //Edit dialog
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 800;
        lp.height = 450;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

        //Navigate to quiz
        easyQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start your activity
                System.out.print("easy quiz clicked");
                Context context = v.getContext();
                Intent intent = new Intent(context, MCQQuizActivity.class);
                intent.putExtra(MCQQuizActivity.regionChosen, regionChosen);
                context.startActivity(intent);
            }
        });


        hardQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ShortAnswerQuizActivity.class);
                intent.putExtra(ShortAnswerQuizActivity.regionChosen, regionChosen);
                context.startActivity(intent);
            }
        });
    }

    public void chooseRegion() {
        //Choose Asia
        asiaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Asia";
                createPopupWindow();
                //launchMCQQuizActivity("Asia");
            }
        });
        //Choose Asia
        africaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Africa";
                createPopupWindow();
                //launchMCQQuizActivity("Africa");
            }
        });
        //Choose Asia
        europeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Europe";
                createPopupWindow();
                //launchMCQQuizActivity("Europe");
            }
        });
        //Choose Asia
        northAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "North America";
                createPopupWindow();
                //launchMCQQuizActivity("North America");
            }
        });
        //Choose South America
        southAmericaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "South America";
                createPopupWindow();
                //launchMCQQuizActivity("South America");
            }
        });
        //Choose Oceania
        oceaniaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionChosen = "Oceania";
                createPopupWindow();
                //launchMCQQuizActivity("Oceania");
            }
        });

    }

    public void launchMCQQuizActivity (String regionChosen){
        //refer when switching screen
        //Intent intent = new Intent(QuizActivity.this, MCQQuizActivity.class); //refer to the current activity in main
        //transport message from MainActivity to DetailActivity

        //start new activity
        //startActivity(intent); //call variable intent


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
