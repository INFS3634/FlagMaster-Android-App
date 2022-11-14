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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private String user_id;
    private Context mContext;
    private ProgressDialog mLoadingBar;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();

        if (mAuth.getCurrentUser() != null) {
            user_id = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot datasnapshot) {
        Log.d(TAG, "checkIfUsernameExists: checking if " + username + " already exists.");

        User user = new User();

        //Loop through datasnapshot to check if username exists
        for (DataSnapshot ds : datasnapshot.child(user_id).getChildren()) {
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
                            user_id = mAuth.getCurrentUser().getUid();
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

    public void addNewUser(String name, String username, String email) {
        User user = new User(user_id, name, username, email);

        //Insert a new users node to the database
        databaseReference.child(mContext.getString(R.string.dbname_users))
                .child(user_id)
                .setValue(user);

        //Insert a new user_account_settings to the database
        UserAccountSettings settings = new UserAccountSettings(username, name, 0, 0, "avatar");
        databaseReference.child(mContext.getString(R.string.dbname_user_account_setting))
                .child(user_id)
                .setValue(settings);
    }

    //Retrieve account settings for user
    public UserSettings getUserSettings(DataSnapshot dataSnapshot) {
        Log.d(TAG, "Get user account settings: retrieving user account settings from firebase");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            //if the node is user_account_setting
            if (ds.getKey().equals(mContext.getString(R.string.dbname_user_account_setting))) {
                Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                try {
                    //name is in both
                    settings.setName(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getName()
                    );
                    settings.setUsername(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getUsername()
                    );
                    settings.setUsername(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getUsername()
                    );
                    settings.setCount_level(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getCount_level()
                    );
                    settings.setCount_point(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getCount_point()
                    );
                    settings.setAvatar(
                            ds.child(user_id)
                                    .getValue(UserAccountSettings.class)
                                    .getAvatar()
                    );
                    Log.d(TAG, "getUserAccountSettings: retrieved user_account_setting info: " + settings.toString());
                } catch (NullPointerException e) {
                    Log.e(TAG, "getUserAccountSettings: NullPointerException: " + e.getMessage());
                }
                if (ds.getKey().equals(mContext.getString(R.string.dbname_users))) {
                    Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                    user.setUsername(
                            ds.child(user_id)
                                    .getValue(User.class)
                                    .getUsername()
                    );
                    user.setEmail(
                            ds.child(user_id)
                                    .getValue(User.class)
                                    .getEmail()
                    );
                    user.setName(
                            ds.child(user_id)
                                    .getValue(User.class)
                                    .getName()
                    );
                    user.setUserID(
                            ds.child(user_id)
                                    .getValue(User.class)
                                    .getUserID()
                    );
                    Log.d(TAG, "getUserAccountSettings: retrieved user info: " + user.toString());

                }

            }

        }
        return new UserSettings(user, settings);
    }


}
