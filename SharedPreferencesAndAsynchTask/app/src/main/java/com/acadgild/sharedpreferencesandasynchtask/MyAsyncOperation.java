package com.acadgild.sharedpreferencesandasynchtask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ssm2349 on 4/24/16.
 */
public class MyAsyncOperation extends AsyncTask<Integer,Integer,Integer>{

    @Override
    protected Integer doInBackground(Integer... params) {
        int result=0;
        for (int i=params[0];i<15;i++){
            try {
                Thread.sleep(1000);
                Log.i("Sleep", new Integer(i).toString());
                result+=i;

                publishProgress(i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("PreExec","Before starting the background task");

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i("StausPublished",values[0].toString());

    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        Log.i("Result",result.toString());

    }
}
