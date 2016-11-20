package code4share.weatherapp.view.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import code4share.weatherapp.R;
import code4share.weatherapp.databinding.ActivityHomeBinding;
import code4share.weatherapp.util.ProgressBarUtil;
import code4share.weatherapp.viewModel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements HomeViewModelContract {
    ArrayAdapter<CharSequence> arrayAdapter;
    ActivityHomeBinding binding;
    Spinner cityDropDownList;
    HomeViewModel homeViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        binding = DataBindingUtil.setContentView (this, R.layout.activity_home);
        cityDropDownList = binding.sprCityList;
        arrayAdapter = ArrayAdapter.createFromResource (this, R.array.city_list, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        cityDropDownList.setAdapter (arrayAdapter);
        homeViewModel = new HomeViewModel (this);
        cityDropDownList.setOnItemSelectedListener (homeViewModel);
    }

    @Override
    public void ShowProgressDialog () {
        if (progressDialog == null)
            progressDialog = ProgressBarUtil.prepareProgressDialog (this);

        if (!progressDialog.isShowing ())
            progressDialog.show ();

    }

    @Override
    public void HideProgressDialog () {
        if (progressDialog != null && progressDialog.isShowing ()) {
            progressDialog.dismiss ();
            progressDialog = null;
        }
    }

    @Override
    public void ShowToastMessage (String msg) {
        Toast.makeText (this, msg, Toast.LENGTH_LONG).show ();
    }

    @Override
    public void notifyDataChanged (Object data) {
        binding.setHomeViewModel ((HomeViewModel) data);
        binding.executePendingBindings ();
    }

    @Override
    public void onPause () {
        super.onPause ();
        HideProgressDialog ();

    }


}
