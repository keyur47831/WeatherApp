package code4share.weatherapp.viewModel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import java.util.Calendar;

import code4share.weatherapp.model.OpenWeatherModel;
import code4share.weatherapp.network.ApiClient;
import code4share.weatherapp.network.ApiInterface;
import code4share.weatherapp.util.Constants;
import code4share.weatherapp.view.activity.HomeViewModelContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by keyur on 11/20/16.
 */

public class HomeViewModel extends BaseObservable implements AdapterView.OnItemSelectedListener {
    String cityName;
    String updateTime;
    String weather;
    String temperature;
    String wind;
    HomeViewModelContract homeViewModelContract;

    public HomeViewModel (HomeViewModelContract callback) {
        homeViewModelContract = callback;
    }

    public String getCityName () {
        return cityName;
    }

    public void setCityName (String cityName) {
        this.cityName = cityName;
    }

    public String getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeather () {
        return weather;
    }

    public void setWeather (String weather) {
        this.weather = weather;
    }

    public String getTemperature () {
        return temperature;
    }

    public void setTemperature (String temperature) {
        this.temperature = temperature;
    }

    public String getWind () {
        return wind;
    }

    public void setWind (String wind) {
        this.wind = wind;
    }

    public void onCitySelected (String data) {

    }

    @Override
    public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
        // homeViewModel.onCitySelected ((String)adapterView.getItemAtPosition (i));
        getServerData ((String) adapterView.getItemAtPosition (i));
    }

    @Override
    public void onNothingSelected (AdapterView<?> parent) {
        // Another interface callback
    }

    private void getServerData (@NonNull String city) {
        ApiInterface weatherService = ApiClient.getClient ().create (ApiInterface.class);
        Call<OpenWeatherModel> call = weatherService.getWeatherData (city + Constants.COUNTRY_APPEND, Constants.UNIT, Constants.API_KEY);
        homeViewModelContract.ShowProgressDialog ();
        call.enqueue (new Callback<OpenWeatherModel> () {
            @Override
            public void onResponse (Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                homeViewModelContract.HideProgressDialog ();
                if (response.isSuccessful ()) {
                    OpenWeatherModel data = response.body ();
                    setCityName (data.getName ());
                    setTemperature (String.valueOf (data.getMain ().getTemp ()));
                    Calendar updateTimeCal = Calendar.getInstance ();
                    updateTimeCal.setTimeInMillis (data.getDt ());
                    setUpdateTime (updateTimeCal.getTime ().toString ());
                    setWeather (data.getWeather ().get (0).getDescription ());
                    setWind (String.valueOf (data.getWind ().getSpeed ()));
                    homeViewModelContract.notifyDataChanged (HomeViewModel.this);
                }
            }

            @Override
            public void onFailure (Call<OpenWeatherModel> call, Throwable t) {
                homeViewModelContract.HideProgressDialog ();
                homeViewModelContract.ShowToastMessage (t.getMessage ());
            }
        });
    }
}
