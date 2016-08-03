package com.example.mmhh2.myapplication;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;




public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge = 0;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_Inquire = 1;
    protected Button btnCharge, btnCheck, btnSettings,btnContact;
    protected EditText numCard;
    protected RelativeLayout mainActivity;
    private static String numberCard, ID, type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCharge = (Button) findViewById(R.id.btnCharge);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnContact = (Button) findViewById(R.id.btnContact);
        numCard = (EditText) findViewById(R.id.numCard);
        mainActivity = (RelativeLayout) findViewById(R.id.mainLayout);
        numberCard = numCard.getText().toString();



        numCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numCard.getText().toString().isEmpty()) {
                    if (!numCard.getText().toString().matches("[0-9]+")) {
                        numCard.setText("");
                    }
                }

            }
        });


        btnCharge.setOnClickListener(charge);

        btnCheck.setOnClickListener(Check);

        btnSettings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainSettings.class);
                startActivity(i);
            }
        });

    }



    OnClickListener charge = new OnClickListener(){

        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge);
                    }

                    return;
                }
            }
            Operations.setVariables(numberCard, ID, type,getBaseContext());
            if (Operations.checkCharge(getBaseContext(),numberCard)){
                startActivity(Operations.call(Operations.getNumberCharge()));
            }
        }


    };

    OnClickListener Check = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE_Inquire);

                    }

                    return;
                }
            }

            Operations.setVariables(type, getBaseContext());
            if (Operations.checkCheck(getBaseContext())) {
                startActivity(Operations.call(Operations.getNumberInquire()));
            }


        }


    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Operations.setVariables(numberCard, ID, type,getBaseContext());
                    if (Operations.checkCharge(getBaseContext(),numberCard)){
                        startActivity(Operations.call(Operations.getNumberCharge()));
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_CALL_PHONE_Inquire: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Operations.setVariables(type,getBaseContext());
                    if (Operations.checkCheck(getBaseContext())) {
                        startActivity(Operations.call(Operations.getNumberInquire()));
                    }


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}


