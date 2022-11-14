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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputUsername, inputEmail, inputPassword;
    private String name, email, username, password;
    Button registerButton;
    private TextView switchToLogin;
    private TextView errorMessage;
    //Firebase
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseMethods firebaseMethods;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private String append;
    //Progress Dialog
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        //Firebase
        mContext = getApplicationContext();
        firebaseMethods = new FirebaseMethods(mContext);
        setupFirebaseAuth();

        inputName = findViewById(R.id.inputName);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        registerButton = findViewById(R.id.registerButton);
        errorMessage = findViewById(R.id.errorMessage);
        switchToLogin = findViewById(R.id.switchToLogin);

        //Loading bar
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputName.getText().toString();
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                boolean checkCredentials = checkCredentials();
                if (checkCredentials == true) {
                    //Save user data into Firebase Database

                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("name", name);
                    hashMap.put("username", username);
                    hashMap.put("email", email);
                    hashMap.put("password", password);

                    databaseReference.child("User")
                            .child(name)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "New user added successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Failed to add new user", Toast.LENGTH_SHORT).show();
                                }
                            });
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
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //When data is changed in the database
                            //1st check: Make sure username is already in use
                            if(firebaseMethods.checkIfUsernameExists(username, dataSnapshot)) {
                                //If username already in use, auto add random character in the end
                                append = databaseReference.push().getKey().substring(3,10);
                                Log.d(TAG, "onDataChange: username already exists. Appending random string to name: " + append);
                            }
                            username = username + append;
                            //Add new user to the database
                            firebaseMethods.addNewUser(name, username, email);
                            Toast.makeText(mContext, "Register successful", Toast.LENGTH_SHORT).show();
                            //Add new user_account_setting to the database

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            //When there is an error changing the database

                        }
                    });
                }
                else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private boolean checkCredentials() {

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
            firebaseMethods.registerNewUser(email, password, username);
            /*//Loading bar
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait while we are setting you up");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();*/

            //Successfully register
            /*mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mLoadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Your account has been created successfully!", Toast.LENGTH_SHORT).show();
                        //Take username
                        newUserID = mAuth.getCurrentUser().getUid();
                        Intent intent = new Intent(RegisterActivity.this, QuizActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }); */
            return true;
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
