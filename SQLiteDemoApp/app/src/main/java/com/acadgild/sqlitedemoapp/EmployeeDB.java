package com.acadgild.sqlitedemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDB extends SQLiteOpenHelper{
    final String TABLE_NAME="employee";
    final String EMPLOYEE_ID="id";
    final String EMPLOYEE_NAME="name";
    final String EMPLOYEE_DEPARTMENT="department";

    public EmployeeDB(Context ctx){
        super(ctx, "com.acadgild.sqlitedemoapp.Employee", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery="create table "+TABLE_NAME+" ( "+EMPLOYEE_ID+" integer(4), "+EMPLOYEE_NAME+" varchar(30), "+EMPLOYEE_DEPARTMENT+" varchar(20) )";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String getAllEmployees(){
        SQLiteDatabase employeeDB=getReadableDatabase();
        Cursor cursor=employeeDB.query(TABLE_NAME, new String[]{EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_DEPARTMENT}, EMPLOYEE_ID + "< ?", new String[]{"100"}, null, null, EMPLOYEE_NAME + " DESC");
        StringBuilder stringBuilder=new StringBuilder();
        if(cursor.moveToFirst()){
            do{
                int employeeIDPosition=cursor.getColumnIndex(EMPLOYEE_ID);
                int id=cursor.getInt(employeeIDPosition);

                String name=cursor.getString(cursor.getColumnIndex(EMPLOYEE_NAME));
                String department=cursor.getString(cursor.getColumnIndex(EMPLOYEE_DEPARTMENT));

                Employee employee=new Employee(id,name,department);
                stringBuilder.append(employee+"\n");

            }while (cursor.moveToNext());
        }
        cursor.close();
        employeeDB.close();
        return stringBuilder.toString();
    }
    void deleteEmployee(){
        SQLiteDatabase emplDB=getWritableDatabase();
        String deleteQuery="delete from "+TABLE_NAME;
        emplDB.delete(TABLE_NAME,EMPLOYEE_DEPARTMENT+" like ?",new String[]{"Man%"});
        emplDB.close();
    }

    public void insertEmployeeRecord(){
        SQLiteDatabase employeeDB= getWritableDatabase();
        ContentValues employeeValues=new ContentValues();
        employeeValues.put(EMPLOYEE_ID,1);
        employeeValues.put(EMPLOYEE_NAME,"PQR");
        employeeValues.put(EMPLOYEE_DEPARTMENT,"Finance");
        employeeDB.insert(TABLE_NAME, null, employeeValues);

        employeeDB.close();
    }

    public void updateEmployee(){
        SQLiteDatabase employeeDb=getWritableDatabase();
        ContentValues emplValues=new ContentValues();
        emplValues.put(EMPLOYEE_DEPARTMENT,"IT");
        employeeDb.update(TABLE_NAME, emplValues, EMPLOYEE_DEPARTMENT + " like ?", new String[]{"Finance"});
        employeeDb.close();
    }

}
