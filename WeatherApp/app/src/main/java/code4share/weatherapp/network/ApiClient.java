package code4share.weatherapp.network;

import code4share.weatherapp.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by keyur on 11/20/16.
 */

public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient () {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder ()
                    .baseUrl (Constants.URL)
                    .addConverterFactory (GsonConverterFactory.create ())
                    .build ();
        }
        return retrofit;
    }
}