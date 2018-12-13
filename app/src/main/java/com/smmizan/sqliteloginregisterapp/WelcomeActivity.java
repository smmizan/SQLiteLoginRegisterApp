package com.smmizan.sqliteloginregisterapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;

    SQLiteHelperClass sqLiteHelperClass;
    SQLiteDatabase sqLiteDatabase;
    String string1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        t1 = (TextView) findViewById(R.id.textName);
        t2 = (TextView) findViewById(R.id.textID);
        t3 = (TextView) findViewById(R.id.textDesignation);
        t4 = (TextView) findViewById(R.id.textPhone);
        t5 = (TextView) findViewById(R.id.textAddress);



        string1 = getIntent().getStringExtra("oko");

        sqLiteHelperClass = new SQLiteHelperClass(this);
        sqLiteDatabase = sqLiteHelperClass.getReadableDatabase();
        Cursor cursor = sqLiteHelperClass.getSingleData(string1,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String U_Name = cursor.getString(0);
            String U_ID = cursor.getString(1);
            String U_Designation = cursor.getString(2);
            String U_Phone = cursor.getString(3);
            String U_Address = cursor.getString(4);

            t1.setText(U_Name);
            t2.setText(U_ID);
            t3.setText(U_Designation);
            t4.setText(U_Phone);
            t5.setText(U_Address);

        }






    }
}
