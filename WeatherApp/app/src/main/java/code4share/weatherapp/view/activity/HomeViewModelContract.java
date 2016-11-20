package code4share.weatherapp.view.activity;

/**
 * Created by keyur on 11/20/16.
 */

public interface HomeViewModelContract {
    void ShowProgressDialog();
    void HideProgressDialog();
    void ShowToastMessage (String msg);
    void notifyDataChanged(Object data);

}
