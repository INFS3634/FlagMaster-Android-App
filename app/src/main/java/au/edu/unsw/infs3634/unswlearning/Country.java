package au.edu.unsw.infs3634.unswlearning;

import android.widget.ImageView;

public class Country {
    private String name;
    private String region;
    private String capital;
    private float area;
    private int population;
    private String flagAPI;

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

    public void setFlag(String flagAPI) {
        this.flagAPI = flagAPI;
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

    public String getFlagAPI() {
        return this.flagAPI;
    }
}
