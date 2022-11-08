package au.edu.unsw.infs3634.unswlearning.countryAPI;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {


    @PrimaryKey
    @NonNull
    private int id;
    private String alpha3Code;
    private String name;
    private String region;
    private String capital;
    private float area;
    private int population;

    //Constructor
    public Country(){

    }

    public Country(String name, String region, String capital, float area, int population) {
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.area = area;
        this.population = population;
    }

    //Setter methods
    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    //Getter methods
    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    public String getCapital() {
        return this.capital;
    }

    public float getArea() {
        return this.area;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
