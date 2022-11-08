package au.edu.unsw.infs3634.unswlearning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputUsername, inputEmail, inputPassword;
    Button registerButton;
    private TextView errorMessage;
    //Firebase
    private FirebaseAuth mAuth;
    //Progress Dialog
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        inputName = findViewById(R.id.inputName);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        registerButton = findViewById(R.id.registerButton);
        errorMessage = findViewById(R.id.errorMessage);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        //Loading bar
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
    }

    private void checkCredentials() {
        String name = inputName.getText().toString();
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (name.isEmpty()) {
            showError(inputName, "Name required");

        } else if (username.isEmpty() || username.length() < 6 || username.length() > 20) {
            showError(inputUsername, "Username must be 6-20 characters long");
        } else if (email.isEmpty() || ! email.contains("@")) {
            showError(inputEmail, "Invalid email");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password must be at least 7 characters long");
        } else {
            //Loading bar
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait while we are setting you up");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            //Successfully register
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Your account has been created successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, QuizActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
