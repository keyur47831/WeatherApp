package code4share.weatherapp.viewModel;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    private String cityName;
    private String updateTime;
    private String weather;
    private String temperature;
    private String wind;
    private HomeViewModelContract homeViewModelContract;

    public HomeViewModel (HomeViewModelContract callback) {
        homeViewModelContract = callback;
    }

    public String getCityName () {
        return cityName;
    }

    private void setCityName (String cityName) {
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

    @Override
    public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
        String selectedItem=(String) adapterView.getItemAtPosition (i);
       if(getCityName ()==null || !getCityName ().equalsIgnoreCase (selectedItem)) {
           getServerData (selectedItem);
       }
    }

    @Override
    public void onNothingSelected (AdapterView<?> parent) {
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
                    setTemperature (String.valueOf (data.getMain ().getTemp ())+"\u2103");
                    SimpleDateFormat sdf = new SimpleDateFormat ("E h:mm a", Locale.ENGLISH);
                    Calendar updateTimeCal = Calendar.getInstance ();
                    updateTimeCal.setTimeInMillis (data.getDt ()*1000L);
                    setUpdateTime (sdf.format (updateTimeCal.getTime ()));
                    setWeather (data.getWeather ().get (0).getDescription ());
                    double kmph=(data.getWind ().getSpeed ()*3600)/1000;
                    setWind (String.valueOf (kmph)+"km/h");
                    homeViewModelContract.notifyDataChanged (HomeViewModel.this);
                }
                else {
                    try {
                        homeViewModelContract.ShowToastMessage (response.errorBody ().string ());
                    } catch (Exception ex) {
                        homeViewModelContract.ShowToastMessage (ex.getMessage ());
                    }
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
