package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private TextView changePhoto;
    private ImageView currentPhoto;
    private TextView helpButton;

    //Firebase
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseMethods mFirebaseMethods;

    //Variables
    private UserSettings mUserSettings;


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

        //Firebase
        /*mContext = getApplicationContext();
        mFirebaseMethods = new FirebaseMethods(mContext);*/

        //Set up Firebase Auth
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");

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
     * Retrieve data from EditTextzview and submit it to the database
     * Before doing so, check to make sure username is unique
     */

    private void saveProfileSettings() {
        newName = currentName.getText().toString();
        newUsername = currentUsername.getText().toString();
        /*final String newEmail = currentEmail.getText().toString();
        final String newPassword = currentPassword.getText().toString();*/

        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(newName);
        checkIfUsernameExists(newUsername);
        //Update new user information to database
        /*databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieve user data from database
                //User result = (User) dataSnapshot.getValue();
                databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(newName);
                checkIfUsernameExists(newUsername);
                //databaseReference.child(FirebaseAuth.getInstance().getUid()).child("username").setValue(newUsername);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("databaseReference failed!");
            }
        });*/


        //Case 1: User changed their username --> require to check unique username
        //Compare username in EditTextView and username loaded from database
                /*if(!mUserSettings.getUser().getUsername().equals(username)) {
                    checkIfUsernameExists(username);
                }
                //Case 2: User changed their display name
                else if (!mUserSettings.getUser().getName().equals(displayName)){
                    mFirebaseMethods.updateDisplayName(displayName);
                }
                //Case 3: User changed Firebase Authenticated Email

            }*/


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
                if (!snapshot.exists()) {
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

    }
