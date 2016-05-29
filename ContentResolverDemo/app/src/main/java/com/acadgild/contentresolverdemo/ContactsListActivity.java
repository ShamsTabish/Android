package com.acadgild.contentresolverdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String []columns={ContactsContract.Contacts.TIMES_CONTACTED, ContactsContract.Contacts.DISPLAY_NAME};

      //  Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, null);

       int []layoutIds={R.id.idOfContact,R.id.nameOfContact};

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.contact_item,/*cursor*/ null,columns,layoutIds,1);

        ListView contactList=(ListView)findViewById(R.id.contactList);

        contactList.setAdapter(adapter);

        getSupportLoaderManager().initLoader(0,null,new ContactsLoader(this,adapter));

        /////////////
        //getContentResolver().query(ContactsContract.Data.CONTENT_URI,)


        //cursor.moveToFirst();
        /*for (String column:cursor.getColumnNames()){
            Log.i("Column Name ",column);
        }
        StringBuilder recordBuilder=new StringBuilder();
        do{
            for(int columnIndex=0;columnIndex<cursor.getCount();columnIndex++)
                recordBuilder.append(cursor.getColumnName(columnIndex)+" => "+cursor.getString(columnIndex)+"\n");
            recordBuilder.append("-----------------------------x-----------------------");
            Log.i("Record: ",recordBuilder.toString());
        }while (cursor.moveToNext());
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
