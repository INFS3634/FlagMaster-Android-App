package au.edu.unsw.infs3634.unswlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.edu.unsw.infs3634.unswlearning.countryAPI.Country;
import au.edu.unsw.infs3634.unswlearning.countryAPI.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryDetailActivity extends AppCompatActivity {
    private static final String TAG = "CountryDetailActivity";
    public static final String INTENT_MESSAGE = "intent_message";
    TextView countryNameTextView, capitalNameTextView, regionNameTextView, populationTextView,
            areaTextView, linkWiki, linkArc;
    ImageView flagImageView, arcLogo;
    private ArrayList<Country> countryList = new ArrayList<>();
    Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        setTitle("Country Dictionary");

        //Assign attributes
        countryNameTextView=findViewById(R.id.countryNameTextView);
        capitalNameTextView=findViewById(R.id.capitalNameTextView);
        regionNameTextView=findViewById(R.id.regionNameTextView);
        populationTextView = findViewById(R.id.populationTextView);
        areaTextView=findViewById(R.id.areaTextView);
        flagImageView=findViewById(R.id.flagImageView);
        linkWiki = findViewById(R.id.linkWiki);
        linkArc = findViewById(R.id.linkArc);
        arcLogo = findViewById(R.id.arcLogo);

        /**
         * Add Back button in ActionBar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        /**
         * Get the intent that started this activity and extract the string
         * Display country details using API
         */

        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_MESSAGE)) {
            String message = intent.getStringExtra(INTENT_MESSAGE);
            Log.d(TAG, "Intent Message = " + message);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://restcountries.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CountryService service = retrofit.create(CountryService.class);
            Call<ArrayList<Country>> responseCall = service.getCountry(message);
            responseCall.enqueue(new Callback<ArrayList<Country>>() {
                @Override
                public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                    Log.d(TAG, "API Call Successful!" + " URL=" + call.request().url().toString());
                    //Take the first result found
                    List<Country> countries = response.body();

                    for (Country country: countries) {
                        countryList.add(country);
                    }
                    country = countryList.get(0);

                    //Display country details
                    if (country != null) {
                        countryNameTextView.setText(country.getName());
                        capitalNameTextView.setText(country.getCapital());
                        regionNameTextView.setText(country.getRegion());
                        populationTextView.setText(String.valueOf(country.getPopulation()));
                        areaTextView.setText(String.valueOf(country.getArea()));

                        //Add Glide library to display country flag images
                        Glide.with(getApplicationContext())
                                .load("https://countryflagsapi.com/png/" + country.getName() + "/")
                                .into(flagImageView);

                        /**
                         * Further external information about country
                         */
                        //Click on Wiki link
                        linkWiki.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + country.getName()));
                                startActivity(intent);
                            }
                        });

                        //Insert UNSW societies' link
                        showSocietyLink(country.getName());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                    Log.d(TAG, "API Call Failure." + "URL=" + call.request().url().toString());
                }
            });
        }
    }

    private void showSocietyLink(String countryName){
        ArrayList<String> societyList = new ArrayList<>(Arrays.asList("China", "Bangladesh",
                "Philippines", "France", "Hong Kong", "Indonesia", "Iran", "Korea", "Leban",
                "Malaysia", "Myanmar", "Japan", "Pakistan", "Singapore", "Vietnam", "Spain",
                "Sri Lanka", "Taiwan", "Thailand", "Turkey"));
        for (String name: societyList) {
            if(countryName.equals(name)){
                linkArc.setVisibility(View.VISIBLE);
                arcLogo.setVisibility(View.VISIBLE);
                linkArc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.arc.unsw.edu.au/clubs/club-admin/clubs-communities/new-international-cultural-clubs-community"));
                        startActivity(intent);
                    }
                });
            }
        }
    }

    // this event will enable the back function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), LearnActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
