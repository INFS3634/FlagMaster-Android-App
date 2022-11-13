package au.edu.unsw.infs3634.unswlearning;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private EditText currentName, currentUsername, currentEmail, currentPassword;
    private Button saveChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_setting);

        currentName = findViewById(R.id.currentName);
        currentUsername = findViewById(R.id.currentUsername);
        currentEmail = findViewById(R.id.currentEmail);
        currentPassword = findViewById(R.id.currentPassword);
        saveChange = findViewById(R.id.saveChange);

        //When user clicks on any of the EditTextView, saveChange button will appear

        //Save changes


    }
}
