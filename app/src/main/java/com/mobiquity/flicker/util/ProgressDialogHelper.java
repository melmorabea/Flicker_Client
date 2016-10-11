package com.mobiquity.flicker.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.mobiquity.flicker.R;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class ProgressDialogHelper {

    private ProgressDialog progressDialog;


    public void show(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context, R.style.TransparentDialogTheme);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hide() {
        if (progressDialog!= null)
            progressDialog.dismiss();
    }

}
