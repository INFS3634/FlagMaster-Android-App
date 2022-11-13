package au.edu.unsw.infs3634.unswlearning.countryAPI;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("alpha3C")
    @Expose
    private String alpha3Code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("area")
    @Expose
    private float area;
    @SerializedName("population")
    @Expose
    private int population;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        flag = 
        this.flag = flag;
    }

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
    public void setId(int id) {
        this.id = id;
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
    public int getId() {
        return id;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

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



}
