package au.edu.unsw.infs3634.unswlearning;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private String append = "";
    private static final String USER = "user";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase
        mContext = RegisterActivity.this;
        Log.d(TAG, "onCreate:started");

        /**
         * Initialize RegisterActivity widget
         */

        Log.d(TAG, "initWidgets: initializing widgets");
        inputName = findViewById(R.id.inputName);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        registerButton = findViewById(R.id.registerButton);
        switchToLogin = findViewById(R.id.switchToLogin);
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        //Set up FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://infs3634-flagmaster-app-default-rtdb.asia-southeast1.firebasedatabase.app");
        //databaseReference = mFirebaseDatabase.getReference("USER");
        databaseReference = mFirebaseDatabase.getReference("USER");


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
                    user = new User(name, username, email, password, 0, 0);
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
                            updateUI(user);
                        } else {
                            Toast.makeText(mContext, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(user);
        Intent intent = new Intent(RegisterActivity.this, QuizActivity.class);
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
        } else if (email.isEmpty() || ! email.contains("@student.unsw.edu.au") ||
                !email.contains("@ad.unsw.edu.au")) {
            showError(inputEmail, "UNSW student email required");
            return false;
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be at least 7 characters long");
            return false;
        } else {
            return true;
        }
    }

   private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
