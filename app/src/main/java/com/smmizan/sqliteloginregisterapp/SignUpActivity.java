package com.smmizan.sqliteloginregisterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    Button button;

    PojoModel pojoModel;

    SQLiteHelperClass dbHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        editText1 = (EditText) findViewById(R.id.text1);
        editText2 = (EditText) findViewById(R.id.text2);
        editText3 = (EditText) findViewById(R.id.text3);
        editText4 = (EditText) findViewById(R.id.text4);
        editText5 = (EditText) findViewById(R.id.text5);
        editText6 = (EditText) findViewById(R.id.text6);
        dbHelperClass = new SQLiteHelperClass(this);

        pojoModel = new PojoModel();

        button = (Button) findViewById(R.id.bSubmitData);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String read1 = editText1.getText().toString();
                String read2 = editText2.getText().toString();
                String read3 = editText3.getText().toString();
                String read4 = editText4.getText().toString();
                String read5 = editText5.getText().toString();
                String read6 = editText6.getText().toString();


                pojoModel.setName(read1);
                pojoModel.setUserID(read2);
                pojoModel.setUserPassword(read3);
                pojoModel.setDesignation(read4);
                pojoModel.setPhone(read5);
                pojoModel.setAddress(read6);


                long row = dbHelperClass.insertData(pojoModel);
                if(row > 0)
                {
                    Toast.makeText(SignUpActivity.this, "insert data " +row+ " successfull", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "insert failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
