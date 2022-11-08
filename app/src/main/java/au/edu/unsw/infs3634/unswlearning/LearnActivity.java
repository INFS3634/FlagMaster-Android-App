package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.unswlearning.countryAPI.Country;
import au.edu.unsw.infs3634.unswlearning.countryAPI.CountryLoreResponse;
import au.edu.unsw.infs3634.unswlearning.countryAPI.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearnActivity extends AppCompatActivity implements RecyclerViewInterface {
    BottomNavigationView bottomNav;
    private static final String TAG = "LearnActivity";
    //Recycler View
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CountryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        //Get the handle to RecyclerView
        recyclerView = findViewById(R.id.rvList);

        //Instantiate a linear recycler view layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter instance with an empty ArrayList of Country objects
        adapter = new CountryAdapter(new ArrayList<Country>(), this);

        //Implement Retrofit to make API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/")//Base URL
                .addConverterFactory(GsonConverterFactory.create()) //Use Gson as converter
                .build();

        //Create object for the service interface
        CountryService service = retrofit.create(CountryService.class);
        Call<CountryLoreResponse> responseCall = service.getCountry();
        responseCall.enqueue(new Callback<CountryLoreResponse>() {
            @Override
            public void onResponse(Call<CountryLoreResponse> call, Response<CountryLoreResponse> response) {
                //Check if it is successful or failed
                Log.d(TAG, "API success!");
                List<Country> countryList = response.body().getData();

                //Supply the retrieved data from API to the adapter to be displayed
                adapter.setData((ArrayList) countryList);
                adapter.sort(CountryAdapter.SORT_METHOD_NAME);
            }

            @Override
            public void onFailure(Call<CountryLoreResponse> call, Throwable t) {
                System.out.println("API Call Failed!");
            }
        });
        //Connect the adapter to the RecyclerView
        recyclerView.setAdapter(adapter);

        //Bottom Navigation
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.learn);

        //Perform item selected listener
        bottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check which item is selected
                switch(item.getItemId()) {
                    case R.id.quiz:
                        startActivity(new Intent(LearnActivity.this, QuizActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.learn:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        System.out.println("Heading to Profile Page");
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(String name) {
        launchCountryDetailActivity(name);
    }

    //Launch country detail page when user taps into items
    private void launchCountryDetailActivity(String name) {
        Intent intent = new Intent(LearnActivity.this, CountryDetailActivity.class);
        intent.putExtra(CountryDetailActivity.countryName, name);
        startActivity(intent);
    }

    @Override
    //Instantiate the menu
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_learn, menu);
        //Create search view
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //Sort by user interaction with the menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortName:
                adapter.sort(CountryAdapter.SORT_METHOD_NAME);
                return true;
            case R.id.sortRegion:
                adapter.sort(CountryAdapter.SORT_METHOD_REGION);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}