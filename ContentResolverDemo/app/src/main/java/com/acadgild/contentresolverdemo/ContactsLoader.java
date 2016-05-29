package com.acadgild.contentresolverdemo;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.SimpleCursorAdapter;

public class ContactsLoader implements LoaderManager.LoaderCallbacks<Cursor> {
    Context context;
    SimpleCursorAdapter adapter;
    ContactsLoader(Context c,SimpleCursorAdapter adapter){
        context=c;
        this.adapter=adapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader=new CursorLoader(context, ContactsContract.Contacts.CONTENT_URI,null,null,null, ContactsContract.Contacts.DISPLAY_NAME);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
