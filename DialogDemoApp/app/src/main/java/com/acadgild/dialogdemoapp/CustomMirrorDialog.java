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
public class CustomMirrorDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Select Color")

                .setView(getActivity().getLayoutInflater().inflate(R.layout.content_manu,null))

                .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "You Clicked Ok!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "You clicked Cancel..", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        return dialogBuilder.create();
    }
}
