package code4share.weatherapp.util;

import android.app.ProgressDialog;
import android.content.Context;

import code4share.weatherapp.R;

/**
 * Created by keyur on 11/20/16.
 */

public class ProgressBarUtil {

    public static ProgressDialog prepareProgressDialog (Context ctx) {
        ProgressDialog progressDialog = new ProgressDialog (ctx);
        progressDialog.setIndeterminate (false);
        progressDialog.setProgressStyle (ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable (false);
        progressDialog.setMessage (ctx.getString (R.string.loading));
        return progressDialog;
    }

}
