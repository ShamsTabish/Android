package com.acadgild.dialogdemoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssm2349 on 4/30/16.
 */
public class MultiSelectListDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List <String> listOfColors=new ArrayList<String>();
        final String []colorsArray=getResources().getStringArray(R.array.colorsArray);

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Colors that your friends like..")
        .setMultiChoiceItems(R.array.colorsArray, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               if(isChecked){
                   listOfColors.add(colorsArray[which]);
               }else {
                   listOfColors.remove(colorsArray[which]);
               }
            }
        })

                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Colors: "+listOfColors, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listOfColors.clear();
                Toast.makeText(getActivity(), "Colors: "+listOfColors, Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        return dialogBuilder.create();
    }
}