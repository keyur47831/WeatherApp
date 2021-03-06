package code4share.weatherapp.model;

/**
 * Created by keyur on 11/20/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


 class Coord {

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    /**
     * @return The lon
     */
    public Double getLon () {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon (Double lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public Double getLat () {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat (Double lat) {
        this.lat = lat;
    }

}
