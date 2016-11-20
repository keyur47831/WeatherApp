package code4share.weatherapp.model;

/**
 * Created by keyur on 11/20/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    /**
     * @return The all
     */
    public int getAll () {
        return all;
    }

    /**
     * @param all The all
     */
    public void setAll (int all) {
        this.all = all;
    }

}