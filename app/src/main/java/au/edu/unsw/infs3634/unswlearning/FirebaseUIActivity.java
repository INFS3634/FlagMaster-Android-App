package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class FirebaseUIActivity extends AppCompatActivity {
    //Create ActivityResultLauncher
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }

    );

    //Start the sign in by creating a sign in intent with preferred sign-in methods
    public void createSignInIntent() {
        //Choose authentication provider
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
                //new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        //Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
        //Dynamic links: https://infs3634-flagmaster-app.web.app
    }

    //Enable email sign-in


    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            //Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        } else {
            //Add a back button to cancel sign-in flow
        }
    }


}
