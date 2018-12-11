package com.smmizan.sqliteloginregisterapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteHelperClass dbHelperClass;

    Button bLogin,bRegister;

    EditText eUserID,eUserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelperClass = new SQLiteHelperClass(this);
        SQLiteDatabase sqLiteDatabase = dbHelperClass.getWritableDatabase();



        eUserID = (EditText) findViewById(R.id.userID) ;
        eUserPassword = (EditText) findViewById(R.id.userPassword) ;



        bLogin = (Button) findViewById(R.id.bLogin);
        bRegister = (Button) findViewById(R.id.bRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = eUserID.getText().toString();
                String userPassword = eUserPassword.getText().toString();


                Boolean vRow = dbHelperClass.cheeckingPassword(userID,userPassword);

                if(vRow == true)
                {
                    Intent iLog = new Intent(MainActivity.this,WelcomeActivity.class);
                    startActivity(iLog);
                    Toast.makeText(MainActivity.this, "Successfully Login !", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "invalid user or password", Toast.LENGTH_SHORT).show();
                }



            }
        });


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });



    }
}
