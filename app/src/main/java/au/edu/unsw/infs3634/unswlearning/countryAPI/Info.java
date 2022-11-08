package au.edu.unsw.infs3634.unswlearning.countryAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("coins_num")
    @Expose
    private Integer countryNum;
    @SerializedName("time")
    @Expose
    private Integer time;

    public Integer getCountryNum() {
        return countryNum;
    }

    public void setCoinsNum(Integer coinsNum) {
        this.countryNum = coinsNum;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
