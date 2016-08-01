package com.example.mmhh2.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    protected Button btnCharge, btnCheck;
    protected EditText numCard;
    protected RelativeLayout mainActivity;
    private long ID = 0;
    public String sCharge;


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
                            sCharge = "*1400*" + numberCard + "*" + ID + "#";
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                        }

                                    return;
                                }
                            }
                            call(sCharge);

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
                sCharge = "*1411#";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        }

                        return;
                    }
                }
                call(sCharge);
            }
        });

    }

    protected void call(String num) {
        Intent iChatge = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",num,null));
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(iChatge);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    call(sCharge);

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


