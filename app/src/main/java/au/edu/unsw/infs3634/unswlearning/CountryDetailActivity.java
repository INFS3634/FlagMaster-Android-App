package au.edu.unsw.infs3634.unswlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CountryDetailActivity extends AppCompatActivity {
    public static String countryName;
    TextView countryNameTextView,capitalNameTextView,regionNameTextView,populationTextView,areaTextView;
    ImageView flagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        countryNameTextView=findViewById(R.id.details_activity_name);
        capitalNameTextView=findViewById(R.id.details_activity_capital);
        regionNameTextView=findViewById(R.id.details_activity_region);
        populationTextView = findViewById(R.id.details_activity_population);
        areaTextView=findViewById(R.id.details_activity_area);
        flagImageView=findViewById(R.id.details_activity_imageView);

        String countryName=getIntent().getStringExtra("countryName");
        String capitalName=getIntent().getStringExtra("capitalName");
        String regionName=getIntent().getStringExtra("regionName");
        String population=getIntent().getStringExtra("population");
        String area=getIntent().getStringExtra("area");
        String flag=getIntent().getStringExtra("flag");

        countryNameTextView.setText("Country Name: "+countryName);
        capitalNameTextView.setText("Capital Name: "+capitalName);
        regionNameTextView.setText("Region Name: "+regionName);
        populationTextView.setText("Population: "+population);
        areaTextView.setText("Area: "+area);
        flagImageView.setImageResource(Integer.parseInt(flag));


        Toast.makeText(getApplicationContext(),population,Toast.LENGTH_SHORT).show();
    }
}
