package com.example.mmhh2.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//test1

public class MainActivity extends AppCompatActivity {
    public Button btnCharge,btnCheck;
    public EditText numCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCharge = (Button)findViewById(R.id.btnCharge);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        numCard = (EditText)findViewById(R.id.numCard);

        numCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    EditText num = (EditText) view.findViewById(R.id.numCard);
                    if (!num.getText().toString().isEmpty()) {
                        if (!num.getText().toString().matches("[0-9]+")) {
                            num.setText("");
                            return true;
                        } else {
                            return false;
                        }
                    }
                }


                return false;
            }
        });
        btnCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numstring = numCard.getText().toString();
                if(!numstring.isEmpty()){
                    if(numstring.matches("[0-9]+")) {
                        if (numstring.length() >= 14) {
                            long numberCard = Long.parseLong(numstring);
                        } else {
                            Toast.makeText(getBaseContext(), "رقم البطاقة قصير جدا", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        numCard.setText("");
                        Toast.makeText(getBaseContext(), "تأكد من كتابة رقم البطاقة", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getBaseContext(),"رقم البطاقة فارغ",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }







}
