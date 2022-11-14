package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    private TextView mName, mUsername, mCountLevel, mCountPoint;
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
    private ImageView settingButton;

    //Firebase
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods mFirebaseMethods;

    //Create a new user (example)
    User user = new User("Paul Ramos", "paul_ramos_01", "hi", "test");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Interface attributes
        mName = findViewById(R.id.name);
        mUsername = findViewById(R.id.username);
        mCountLevel = findViewById(R.id.countLevelPassed);
        mCountPoint = findViewById(R.id.countTotalPoints);

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
        mFirebaseMethods = new FirebaseMethods(mContext);

        //Setup FirebaseAuth
        setupFirebaseAuth();

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

        //Setting
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSetting();
            }
        });
        //Set up badges

    }

    private void displayUserProfile(UserSettings userSettings) {
        //User user = userSettings.getUser();
        UserAccountSettings settings = userSettings.getSettings();

        mName.setText(settings.getName());
        mUsername.setText(settings.getUsername());
        mCountLevel.setText(String.valueOf(settings.getCount_level()));
        mCountPoint.setText(String.valueOf(settings.getCount_point()));

    }

    public void launchSetting() {
        Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
        //switch to Setting screen
        startActivity(intent);
    }

    /*public void editProfile() {
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

    }*/
    //Set up Firebase Auth
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                }
                else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        //Get the data snapshot to read data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieve user data from database
                displayUserProfile(mFirebaseMethods.getUserSettings(dataSnapshot));

                //Retrieve user profile

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
