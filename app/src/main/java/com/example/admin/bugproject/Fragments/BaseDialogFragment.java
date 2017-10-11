package com.example.admin.bugproject.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Admin on 9/6/2017.
 */

public class BaseDialogFragment extends DialogFragment {
    protected ProgressDialog progressDialog;

    protected void showProgressDialog(boolean isShown) {
        showProgressDialog(isShown, null);
    }

    protected void showProgressDialog(boolean isShown, String message) {
        try {
            if (this.progressDialog == null) {
                progressDialog = new ProgressDialog(this.getActivity());

                progressDialog.setMessage("Waiting...");
            }

            if (this.progressDialog != null) {
                if (isShown) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        }catch (Exception e){

        }
    }

    protected AlertDialog.Builder newDialogBuilder(String title, String message){

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            return builder;
        }catch (Exception e){
            return null;
        }

    }
}
