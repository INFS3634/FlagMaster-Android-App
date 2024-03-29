package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SettingActivity extends AppCompatActivity {

    private EditText currentName, currentUsername, currentEmail, currentPassword;
    private String newName, newUsername;
    private Button saveChange;
    private TextView changePhoto, signOutButton;
    private ImageView currentPhoto;
    private TextView helpButton;

    //Firebase
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        currentName = findViewById(R.id.currentName);
        currentUsername = findViewById(R.id.currentUsername);
        currentEmail = findViewById(R.id.currentEmail);
        currentPassword = findViewById(R.id.currentPassword);
        saveChange = findViewById(R.id.saveChange);
        changePhoto = findViewById(R.id.changePhoto);
        currentPhoto = findViewById(R.id.currentPhoto);
        helpButton = findViewById(R.id.helpButton);
        signOutButton = findViewById(R.id.signOutButton);

        //Disable EditTextView password and email
        currentEmail.setEnabled(false);
        currentPassword.setEnabled(false);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        //Set up Firebase Auth
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");

        /**
         * Sign out, back to Sign in page
         */
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class); //refer to the current activity in main
                startActivity(intent);
            }
        });

        //Pre-load current information to EditTextView
        databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieve user data from database
                //User result = (User) dataSnapshot.getValue();
                User result = dataSnapshot.getValue(User.class);

                if (result != null) {
                    Log.d(TAG, "retrieveDataFromDB");
                    currentName.setText(result.getName());
                    currentUsername.setText(result.getUsername());
                    currentPassword.setText(result.getPassword());
                    currentEmail.setText(result.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("databaseReference failed!");
            }
        });
        //When user clicks on any of the EditTextView, saveChange button will appear

        //Save changes
        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to save changes");
                saveProfileSettings();
                backToProfile();
            }
        });

        /**
         * Navigate to Help page
         */
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HelpActivity.class); //refer to the current activity in main
                //switch to Register screen
                startActivity(intent);
            }
        });
    }

    /**
     * Retrieve data from EditTextView and submit it to the database
     * Before doing so, check to make sure username is unique
     */

    private void saveProfileSettings() {
        newName = currentName.getText().toString();
        newUsername = currentUsername.getText().toString();

        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(newName);
        checkIfUsernameExists(newUsername);

        /**
         * Check if @param username already exists in the database
         * @param username
         */
    }
    private void checkIfUsernameExists(String username) {
        Log.d(TAG, "checkIfUsernameExists: checking if " + username + " already exists");

        DatabaseReference messageRef = mFirebaseDatabase.getReference();
        //Use Query database
        Query query = messageRef
                .child("USER")
                .orderByChild("username")
                .equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Update username
                    databaseReference.child(FirebaseAuth.getInstance().getUid()).child("username").setValue(newUsername);
                    //Toast.makeText(getApplicationContext(), "Saved new username", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        backToProfile();
        return true;
    }

    private void backToProfile() {
        Intent myIntent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivityForResult(myIntent, 0);
    }

    }
