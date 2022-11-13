package au.edu.unsw.infs3634.unswlearning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private Context mContext;
    private ProgressDialog mLoadingBar;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot datasnapshot) {
        Log.d(TAG, "checkIfUsernameExists: checking if " + username + " already exists.");

        User user = new User();

        //Loop through datasnapshot to check if username exists
        for (DataSnapshot ds: datasnapshot.getChildren()) {
            Log.d(TAG, "checkIfUsernameExists: datasnapshot: " + ds);

            //Retrieve username from database
            user.setUsername(ds.getValue(User.class).getUsername());

            if (user.getUsername().equals(username)) {
                Log.d(TAG, "checkIfUsernameExist: FOUND A MATCH: " + user.getUsername());
                return true;
            }
        }
        return false;
    }

    //Register a new email and password to Firebase Authentication
    public void registerNewUser(final String email, String password, final String username) {
        //Loading bar
        mLoadingBar.setTitle("Registration");
        mLoadingBar.setMessage("Please wait while we are setting you up");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mLoadingBar.dismiss();
                            Toast.makeText(mContext, "Your account has been created successfully!", Toast.LENGTH_SHORT).show();
                            //Verify username
                            userID = mAuth.getCurrentUser().getUid();
                            Intent intent = new Intent(mContext, QuizActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            //mContext.startActivity();
                        } else {
                            Toast.makeText(mContext, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ;
                });

    }
}
