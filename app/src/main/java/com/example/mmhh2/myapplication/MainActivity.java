package com.example.mmhh2.myapplication;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge = 0;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_Inquire = 1;
    protected Button btnCharge, btnCheck, btnSettings;
    protected EditText numCard;
    protected RelativeLayout mainActivity;
    private static String numberCard,ID,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCharge = (Button) findViewById(R.id.btnCharge);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnSettings = (Button) findViewById(R.id.btnSettings);
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
                numberCard = numCard.getText().toString();
                if (!numberCard.isEmpty()) {
                    if (numberCard.matches("[0-9]+")) {
                        if (numberCard.length() >= 14) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge);
                                        }

                                    return;
                                }
                            }
                            Operations.setVariables(numberCard,ID,type);
                            startActivity(Operations.call(Operations.getNumberCharge()));

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
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE_Inquire);

                        }

                        return;
                    }
                }
                Operations.setVariables(type);
                startActivity(Operations.call(Operations.getNumberInquire()));

            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainSettings.class);
                startActivity(i);
            }
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE_Charge: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Operations.setVariables(numberCard,ID,type);
                    startActivity(Operations.call(Operations.getNumberCharge()));

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
                    Operations.setVariables(type);
                    startActivity(Operations.call(Operations.getNumberInquire()));


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }

            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }


}


