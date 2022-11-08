package au.edu.unsw.infs3634.unswlearning.countryAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {
    //Get information by id
    @GET("v2/all?fields=name,alpha3Code,capital,population,area,region")
    Call<CountryLoreResponse> getCountry();



    //https://restcountries.com/v3.1/name/japan?fields=name,cca3,capital,population,area,region,languages
    //https://restcountries.com/v2/all?fields=name,alpha3Code,capital,population,area,region
}
