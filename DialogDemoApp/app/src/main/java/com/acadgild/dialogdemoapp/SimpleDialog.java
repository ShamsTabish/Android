package com.acadgild.dialogdemoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by ssm2349 on 4/30/16.
 */
public class SimpleDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Select Color");
        dialogBuilder.setMessage("What is your favorite color? ")
                .setPositiveButton("Yes, delete!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "File Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No, Save!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "File, Saved..", Toast.LENGTH_SHORT).show();
                    }
                });
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        return dialogBuilder.create();
    }
}
