package au.edu.unsw.infs3634.unswlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    String[] regions = { "No Filter","Asia", "Africa", "Europe", "Oceania", "South America"};

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Country> data;
    static View.OnClickListener myOnClickListener;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    private TextView switchToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startButton = findViewById(R.id.startButton);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        switchToRegister = findViewById(R.id.switchToRegister);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(MainActivity.this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,regions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        myOnClickListener = new MyOnClickListener(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerVIew);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Country>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new Country(
                    MyData.nameArray[i],
                    MyData.regionArray[i],
                    MyData.capitalArray[i],
                    MyData.areaArray[i],
                    MyData.populationArray[i],
                    MyData.drawableArray[i]
            ));
        }

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        data.clear();
        adapter.notifyDataSetChanged();
        if(regions[position].equals("No Filter")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                data.add(new Country(
                        MyData.nameArray[i],
                        MyData.regionArray[i],
                        MyData.capitalArray[i],
                        MyData.areaArray[i],
                        MyData.populationArray[i],
                        MyData.drawableArray[i]
                ));
            }
            adapter.notifyDataSetChanged();
        }

        if(regions[position].equals("Asia")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(MyData.regionArray[i].equals("Asia")) {
                    data.add(new Country(
                            MyData.nameArray[i],
                            MyData.regionArray[i],
                            MyData.capitalArray[i],
                            MyData.areaArray[i],
                            MyData.populationArray[i],
                            MyData.drawableArray[i]
                    ));
                }
            }
            adapter.notifyDataSetChanged();
        }

        if(regions[position].equals("Africa")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(MyData.regionArray[i].equals("Africa")) {
                    data.add(new Country(
                            MyData.nameArray[i],
                            MyData.regionArray[i],
                            MyData.capitalArray[i],
                            MyData.areaArray[i],
                            MyData.populationArray[i],
                            MyData.drawableArray[i]
                    ));
                }
            }
            adapter.notifyDataSetChanged();
        }

        if(regions[position].equals("Europe")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(MyData.regionArray[i].equals("Europe")) {
                    data.add(new Country(
                            MyData.nameArray[i],
                            MyData.regionArray[i],
                            MyData.capitalArray[i],
                            MyData.areaArray[i],
                            MyData.populationArray[i],
                            MyData.drawableArray[i]
                    ));
                }
            }
            adapter.notifyDataSetChanged();
        }

        if(regions[position].equals("Oceania")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(MyData.regionArray[i].equals("Oceania")) {
                    data.add(new Country(
                            MyData.nameArray[i],
                            MyData.regionArray[i],
                            MyData.capitalArray[i],
                            MyData.areaArray[i],
                            MyData.populationArray[i],
                            MyData.drawableArray[i]
                    ));
                }
            }
            adapter.notifyDataSetChanged();
        }

        if(regions[position].equals("South America")){
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if(MyData.regionArray[i].equals("South America")) {
                    data.add(new Country(
                            MyData.nameArray[i],
                            MyData.regionArray[i],
                            MyData.capitalArray[i],
                            MyData.areaArray[i],
                            MyData.populationArray[i],
                            MyData.drawableArray[i]
                    ));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            String countryName= data.get(selectedItemPosition).getName();
            String capitalName= data.get(selectedItemPosition).getCapital();
            String regionName= data.get(selectedItemPosition).getRegion();
            String population= data.get(selectedItemPosition).getPopulation();
            String area= data.get(selectedItemPosition).getArea();
            int flag= data.get(selectedItemPosition).getFlag();

            Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
            intent.putExtra("countryName",countryName);
            intent.putExtra("capitalName",capitalName);
            intent.putExtra("regionName",regionName);
            intent.putExtra("population",population);
            intent.putExtra("area",area);
            intent.putExtra("flag",String.valueOf(flag));
            startActivity(intent);
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                launchQuizActivity();

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