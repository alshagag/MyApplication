package com.example.mmhh2.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    protected Button btnCharge, btnCheck;
    protected EditText numCard;
    protected RelativeLayout mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCharge = (Button) findViewById(R.id.btnCharge);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        numCard = (EditText) findViewById(R.id.numCard);
        mainActivity = (RelativeLayout) findViewById(R.id.mainLayout);


        numCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numCard.getText().toString().isEmpty()) {
                    if (!numCard.getText().toString().matches("[0-9]+")) {
                        numCard.setText("");
                    }
                }

            }
        });


        btnCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numstring = numCard.getText().toString();
                if (!numstring.isEmpty()) {
                    if (numstring.matches("[0-9]+")) {
                        if (numstring.length() >= 14) {
                            long numberCard = Long.parseLong(numstring);
                        } else {
                            Toast.makeText(getBaseContext(), "رقم البطاقة قصير جدا", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "لا يوجد رقم بطاقة", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getBaseContext(), "لا يوجد رقم بطاقة", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}


