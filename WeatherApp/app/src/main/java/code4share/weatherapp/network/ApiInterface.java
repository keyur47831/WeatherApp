package code4share.weatherapp.network;

import code4share.weatherapp.model.OpenWeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by keyur on 11/20/16.
 */

public interface ApiInterface {
    @GET("/data/2.5/weather")
    Call<OpenWeatherModel> getWeatherData(@Query("q") String cityname,@Query ("units") String units,@Query ("appid")String appID);
}
