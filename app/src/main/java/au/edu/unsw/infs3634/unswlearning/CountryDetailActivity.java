package au.edu.unsw.infs3634.unswlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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
    TextView countryNameTextView, capitalNameTextView, regionNameTextView, populationTextView, areaTextView, linkWiki;
    ImageView flagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);

        countryNameTextView=findViewById(R.id.details_activity_name);
        capitalNameTextView=findViewById(R.id.details_activity_capital);
        regionNameTextView=findViewById(R.id.details_activity_region);
        populationTextView = findViewById(R.id.details_activity_population);
        areaTextView=findViewById(R.id.details_activity_area);
        flagImageView=findViewById(R.id.details_activity_imageView);
        linkWiki = findViewById(R.id.linkWiki);

        //Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_MESSAGE)) {
            String message = intent.getStringExtra(INTENT_MESSAGE);
            Log.d(TAG, "Intent Message = " + message);
            // Implement Retrofit to make API call
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://restcountries.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

// Create object to make API call
            CountryService service = retrofit.create(CountryService.class);
            Call<ArrayList<Country>> responseCall = service.getCountry(message);
            responseCall.enqueue(new Callback<ArrayList<Country>>() {
                @Override
                public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                    Log.d(TAG, "API Call Successful!" + " URL=" + call.request().url().toString());
                    //Take the first result found
                    Country country = response.body().get(0);
                    if (country != null) {
                        countryNameTextView.setText(country.getName());
                        capitalNameTextView.setText(country.getCapital());
                        regionNameTextView.setText(country.getRegion());
                        populationTextView.setText(country.getPopulation());
                        areaTextView.setText(String.valueOf(country.getArea()));

                        //Add Glide library to display country flag images
                        Glide.with(getApplicationContext())
                                .load("https://countryflagsapi.com/png/" + country.getName() + "/")
                                .into(flagImageView);

                        //Click on Wiki link
                        linkWiki.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + country.getName()));
                                startActivity(intent);
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                    Log.d(TAG, "API Call Failure." + "URL=" + call.request().url().toString());
                }
            });
        }
    }
}