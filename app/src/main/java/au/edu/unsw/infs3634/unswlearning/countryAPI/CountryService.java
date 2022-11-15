package au.edu.unsw.infs3634.unswlearning.countryAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CountryService {
    //Get all country
    @GET("v2/all?fields=name,alpha3Code,capital,population,area,region,flag")
    //Call<CountryLoreResponse> getCountries();
    Call<ArrayList<Country>> getAllCountries();

    //Get country by name
    @GET("v2/name/{name}?fields=name,alpha3Code,capital,population,area,region,flag")
    Call<ArrayList<Country>> getCountry(@Path("name") String name);

}
