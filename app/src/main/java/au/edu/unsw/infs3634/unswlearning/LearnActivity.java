package au.edu.unsw.infs3634.unswlearning;

import android.app.ProgressDialog;
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
    private RecyclerView recyclerView;
    ArrayList<Country> countryList = new ArrayList<>();
    private CountryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        setTitle("Country Dictionary");

        //Get the handle to RecyclerView
        recyclerView = findViewById(R.id.rvList);

        //Instantiate a linear recycler view layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an Adapter instance with an empty Arraylist of Country objects
        adapter = new CountryAdapter(countryList, this);

        //Implement Retrofit to make API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/")//Base URL
                .addConverterFactory(GsonConverterFactory.create()) //Use Gson as converter
                .build();

        //Create object for the service interface
        CountryService service = retrofit.create(CountryService.class);
        Call<ArrayList<Country>> responseCall = service.getAllCountries();
        responseCall.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                //Check if it is successful or failed
                Log.d(TAG, "API call successful!");

                List<Country> countries = response.body();

                for (Country country: countries) {
                    countryList.add(country);
                }

                //Display data into RecyclerView
                PutDataIntoRecyclerView(countryList);
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {

            }
        });

        recyclerView.setAdapter(adapter);

        /**
         * Set up Bottom Navigation View
         */

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

    private void PutDataIntoRecyclerView(ArrayList<Country> countryList) {
        //Create an adapter instance with an empty ArrayList of Country objects
        //Instantiate a linear recycler view layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //default display by name
        adapter.sort(CountryAdapter.SORT_METHOD_NAME);
    }

    @Override
    public void onItemClick(String name) {
        launchCountryDetailActivity(name);
    }

    //Launch country detail page when user taps into items
    private void launchCountryDetailActivity(String msg) {
        Intent intent = new Intent(LearnActivity.this, CountryDetailActivity.class);
        intent.putExtra(CountryDetailActivity.INTENT_MESSAGE, msg);
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