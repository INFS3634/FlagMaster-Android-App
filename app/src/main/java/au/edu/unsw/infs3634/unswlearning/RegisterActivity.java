package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    private Context mContext;
    private EditText inputName, inputUsername, inputEmail, inputPassword;
    private String name, email, username, password;
    Button registerButton;
    private TextView switchToLogin;
    //Progress Dialog
    private ProgressDialog mLoadingBar;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseMethods firebaseMethods;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private String append = "";
    private static final String USER = "user";
    private User user;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase
        mContext = RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);
        Log.d(TAG, "onCreate:started");

        /**
         * Initialize RegisterActivity widget
         */

        Log.d(TAG, "initWidgets: initializating widgets");
        inputName = findViewById(R.id.inputName);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        registerButton = findViewById(R.id.registerButton);
        switchToLogin = findViewById(R.id.switchToLogin);
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        //Set up FirebaseAuth
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");
        mAuth = FirebaseAuth.getInstance();

        /**
         * Init register button
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take user input
                name = inputName.getText().toString();
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();


                if(checkCredentials(name, username, email, password)) {
                    mLoadingBar.setTitle("Registration");
                    mLoadingBar.setMessage("Please wait while we are setting you up");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    mLoadingBar.dismiss();
                    user = new User(name, username, email, password);
                    registerNewUser(email, password);
                    Log.d(TAG, "checkCredentials: added a new user to database");
                }
            }
        });


        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class); //refer to the current activity in main
                //switch to Register screen
                startActivity(intent);
            }
        });
    }

    public void registerNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "Your account has been created successfully!", Toast.LENGTH_SHORT).show();
                            //Verify username
                            FirebaseUser user = mAuth.getCurrentUser();
                            //userID = user.getUid();
                            updateUI(user);
                        } else {
                            Toast.makeText(mContext, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser currentUser) {
        String keyId = databaseReference.push().getKey();
       /* databaseReference.child("user")
                .child(userID)
                .setValue(user);*/
        databaseReference.child(keyId).setValue(user);
        Intent intent = new Intent(RegisterActivity.this, QuizActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean checkCredentials(String name, String username, String email, String password) {
        Log.d(TAG, "checkCredentials");

        if (name.isEmpty()) {
            showError(inputName, "Name required");
            return false;
        } else if (username.isEmpty() || username.length() < 6 || username.length() > 20) {
            showError(inputUsername, "Username must be 6-20 characters long");
            return false;
        } else if (email.isEmpty() || ! email.contains("@")) {
            showError(inputEmail, "Invalid email");
            return false;
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be at least 7 characters long");
            return false;
        } else {
            return true;
        }
    }

    /*
    ----------------------------Firebase---------------------------------------------
     */
    /**
     * Check if username exists
     * @param username
     */
    /*private void checkIfUsernameExists(final String username) {
        Log.d(TAG, "checkIfUsernameExists: checking if " + username + " already exists");

        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference();
        //Use Query database to check username
        Query query = databaseReference
                .child(getString(R.string.dbname_users))
                .orderByChild(getString(R.string.field_username))
                .equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //If username already exists
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    if (singleSnapshot.exists()) {
                        Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH: " + singleSnapshot.getValue(User.class).getUsername());
                        //Auto append random character in the end
                        append = databaseReference.push().getKey().substring(3, 10);
                        Log.d(TAG, "onDataChange: username already exists. Appending random string to name: " + append);
                    }
                }
                String mUsername = username + append;

                //Add new user to the database
                firebaseMethods.addNewUser(name, mUsername, email, password);
                Toast.makeText(mContext, "addNewUser successful", Toast.LENGTH_SHORT).show();

                //mAuth.signOut();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
    /**
     * Set up FirebaseAuth
     */
    /*private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //1st check: Make sure username is not already in use
                            if(firebaseMethods.checkIfUsernameExists(username, dataSnapshot)) {
                                //If exists, append random characters
                                append = databaseReference.push().getKey().substring(3,7);
                                Log.d(TAG, "onDataChange: username already exists. Appending random string to username: " + append);
                            }
                    String mUsername = username + append;
                     //checkIfUsernameExists(username);
                    // add new user to the database
                    firebaseMethods.addNewUser(name, mUsername, email, password);
                    Toast.makeText(mContext, "addNewUser successful", Toast.LENGTH_SHORT).show();

                            //mAuth.signOut();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            //When there is an error changing the database
                        }
                    });
                    //finish();
                }
                else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }*/

    /*@Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!= null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/

   private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
