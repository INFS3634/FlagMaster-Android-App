package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    String[] regions = { "No Filter","Asia", "Africa", "Europe", "Oceania", "South America"};
    */

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    private TextView switchToRegister;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFirebaseAuth();

        startButton = findViewById(R.id.startButton);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        switchToRegister = findViewById(R.id.switchToRegister);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(MainActivity.this);

        //mAuth.signOut(); //Auto sign out when running the app again

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
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

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase authentication");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = (FirebaseAuth.AuthStateListener) (firebaseAuth) -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {
                //User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
            }
            else {
                //User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };
    }


    private void checkCredentials() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();


        if (email.isEmpty() || !email.contains("@")) {
            showError(loginEmail, "Invalid email");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(loginPassword, "Password must be at least 7 characters long");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait while we are logging you in");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void launchQuizActivity() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class); //refer to the current activity in main
        //start new activity
        System.out.println("Built!");
        startActivity(intent);
    }
}