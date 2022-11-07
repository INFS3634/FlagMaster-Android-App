package au.edu.unsw.infs3634.unswlearning;

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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this, QuizActivity.class); //refer to the current activity in main
                //start new activity
                startActivity(intent);
            }
        });
    }
}
