package au.edu.unsw.infs3634.unswlearning.countryAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import au.edu.unsw.infs3634.unswlearning.countryAPI.Country;
import au.edu.unsw.infs3634.unswlearning.countryAPI.Info;

public class CountryLoreResponse {

    @SerializedName("data")
    @Expose
    private List<Country> data = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
