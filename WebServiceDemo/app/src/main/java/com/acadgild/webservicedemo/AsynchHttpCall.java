package com.acadgild.webservicedemo;

import android.os.AsyncTask;
import android.widget.EditText;

/**
 * Created by ssm2349 on 6/11/16.
 */
public class AsynchHttpCall extends AsyncTask<String,Integer,String> {
    EditText texBox;
    AsynchHttpCall (EditText textBox){
        this.texBox=textBox;
    }
    @Override
    protected String doInBackground(String... params) {

        WebServiceCall callMusicService=new WebServiceCall();
        String data=callMusicService.getListOfTopTagsInXMLFormat();
        return data;

    }

    @Override
    protected void onPostExecute(String resultData) {
        super.onPostExecute(resultData);
        texBox.setText(resultData);
    }
}
