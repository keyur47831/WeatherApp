package code4share.weatherapp.model;

/**
 * Created by keyur on 11/20/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;

    /**
     * @return The speed
     */
    public Double getSpeed () {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed (Double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Integer getDeg () {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg (Integer deg) {
        this.deg = deg;
    }

}


