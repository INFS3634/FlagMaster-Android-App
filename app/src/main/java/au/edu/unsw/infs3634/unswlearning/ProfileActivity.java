package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    private TextView mName, mUsername, mCountLevel, mCountPoint;
    private ImageView mProfilePhoto;
    private ImageView africaBadge;
    private ImageView asiaBadge;
    private ImageView europeBadge;
    private ImageView northAmericaBadge;
    private ImageView southAmericaBadge;
    private ImageView oceaniaBadge;

    //Edit profile
    private ImageView settingButton;

    //Firebase
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Interface attributes
        mName = findViewById(R.id.mName);
        mUsername = findViewById(R.id.mUsername);
        mCountLevel = findViewById(R.id.mCountLevel);
        mCountPoint = findViewById(R.id.mCountPoint);
        mProfilePhoto = findViewById(R.id.mProfilePhoto);

        africaBadge = findViewById(R.id.africaBadge);
        asiaBadge = findViewById(R.id.asiaBadge);
        europeBadge = findViewById(R.id.europeBadge);
        northAmericaBadge = findViewById(R.id.northAmericaBadge);
        southAmericaBadge = findViewById(R.id.southAmericaBadge);
        oceaniaBadge = findViewById(R.id.oceaniaBadge);

        //Bottom Navigation View
        bottomNav = findViewById(R.id.bottomNavigationView);
        //Setting
        settingButton = findViewById(R.id.settingButton);
        //Firebase
        mContext = getApplicationContext();

        //Setup FirebaseAuth
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");

        databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieve user data from database
                User result = dataSnapshot.getValue(User.class);

                if (result != null) {
                    Log.d(TAG, "retrieveDataFromDB");
                    mName.setText(result.getName());
                    mUsername.setText(result.getUsername());
                    mCountLevel.setText(String.valueOf(result.getCountLevel()));
                    mCountPoint.setText(String.valueOf(result.getCountPoint()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("databaseReference failed!");
            }
        });

        /**
         * Set up Bottom Navigation View
         */
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

        /**
         * Setting screen
         */
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSetting();
            }
        });
    }

    public void launchSetting() {
        Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
        //switch to Setting screen
        startActivity(intent);
    }
}
