package com.smmizan.sqliteloginregisterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mizan on 10/12/2018.
 */

public class SQLiteHelperClass extends SQLiteOpenHelper {

    private static final String DB_Name ="mydb_log.db";
    private static final String TABLE_NAME ="my_tablessu";
    private static final String ID ="id";
    private static final String Name ="name";
    private static final String USER_ID ="user_id";
    private static final String USER_PASSWORD ="password";
    private static final String USER_DESIGNATION ="designation";
    private static final String USER_PHONE ="phone";
    private static final String USER_ADDRESS ="address";
    private static final int DB_Version = 2;

    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Name+" VARCHAR(200) NOT NULL,"+USER_ID+" VARCHAR(200) NOT NULL,"+USER_PASSWORD+" VARCHAR(200) NOT NULL,"+USER_DESIGNATION+" VARCHAR(200) NOT NULL,"+USER_PHONE+" VARCHAR(200) NOT NULL,"+USER_ADDRESS+" VARCHAR(200) NOT NULL);";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

    private Context context;

    public SQLiteHelperClass(Context context) {
        super(context, DB_Name, null, DB_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try
        {

            Log.e("mizan","on Create");
            Toast.makeText(context, "oncreate method are call", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }catch (Exception e)
        {
            Log.e("mizan","error upgrading"+e);
            Toast.makeText(context, "on create fail"+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            Log.e("mizan","upgrade");
            Toast.makeText(context, "on Upgrade is calling", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e)
        {
            Log.e("mizan","error upgrading"+e);
            Toast.makeText(context, "on Upgrade is failure"+e, Toast.LENGTH_SHORT).show();
        }

    }


    public long insertData(PojoModel pojoModel)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name,pojoModel.getName());
        contentValues.put(USER_ID,pojoModel.getUserID());
        contentValues.put(USER_PASSWORD,pojoModel.getUserPassword());
        contentValues.put(USER_DESIGNATION,pojoModel.getDesignation());
        contentValues.put(USER_PHONE,pojoModel.getPhone());
        contentValues.put(USER_ADDRESS,pojoModel.getAddress());

        long row = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return row;


    }



    public boolean cheeckingPassword(String userName,String userPassword)
    {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result = false;

        if (cursor.getCount() == 0)
        {
            Toast.makeText(context, "Invalid User or Password", Toast.LENGTH_SHORT).show();
        }
        else

        {
            while (cursor.moveToNext())
            {
                String user = cursor.getString(1);
                String pass = cursor.getString(2);

                if(user.equals(userName) && pass.equals(userPassword))
                {
                    result = true;
                    break;
                }

            }
        }

        return result;


    }










}
