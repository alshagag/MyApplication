package com.example.mmhh2.myapplication;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by AbuAli on 8/2/2016.
 */
public class Operations {
    private static String numberCard,ID,type,numberCharge,numberInquire;
    private static int typeNumberCharge,typeNumberInquire;
    public static void setVariables(String n , String i,String t)
    {
        numberCard = n;
        ID = i;
        type = t;
        switch (type){
            case "stc":{
                typeNumberCharge = 155;
                numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                break;

            }
            case "mobily":{
                typeNumberCharge = 1400;
                numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                break;
            }
            case "zin":{
                typeNumberCharge = 141;
                numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                break;
            }
            default:
                break;
        }



    }
    public static void setVariables(String t)
    {

        type = t;
        switch (type){
            case "stc":{
                typeNumberInquire = 166;
                numberInquire = "*" + typeNumberInquire + "#";
                break;

            }
            case "mobily":{
                typeNumberInquire = 1411;
                numberInquire = "*" + typeNumberInquire + "#";
                break;
            }
            case "zin":{
                typeNumberInquire = 142;
                numberInquire = "*" + typeNumberInquire + "#";
                break;
            }
            default:
                break;
        }



    }
    public static Intent call(String number){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",number,null));
        return i;
    }

    public static String getNumberCharge()
    {
        return numberCharge;
    }

    public static String getNumberInquire()
    {
        return numberInquire;
    }
}
