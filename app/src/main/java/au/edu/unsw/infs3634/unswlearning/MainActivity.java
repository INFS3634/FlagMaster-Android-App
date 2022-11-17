package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    private TextView switchToRegister;
    private Button continueAsGuest;
    private String email,password;

    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        //Set up Firebase Authentication
        setupFirebaseAuth();
        mContext = this;

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        switchToRegister = findViewById(R.id.switchToRegister);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(MainActivity.this);

        mAuth.signOut(); //Auto sign out when running the app again

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = loginEmail.getText().toString();
                password = loginPassword.getText().toString();
                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(mContext, "Please enter your email and password to sign in", Toast.LENGTH_SHORT).show();
                } else {
                    checkCredentials();
                }

            }
        });

        switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class); //refer to the current activity in main
                //switch to Register screen
                startActivity(intent);
            }
        });
    }

    //Set up Firebase Auth
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = mFirebaseDatabase.getReference("USER");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //Check if user is logged in
                //checkCurrentUser(user);
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

    /**
     * Check credentials
     */
    private void checkCredentials() {

        mLoadingBar.setTitle("Login");
        mLoadingBar.setMessage("Please wait while we are logging you in");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mLoadingBar.dismiss();
                    //retrieveDataFromDB();
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    mLoadingBar.dismiss();
                    //loginPassword.setError("Wrong password or email");
                    Toast.makeText(mContext, "Wrong password or email. Please try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}