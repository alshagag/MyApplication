package com.example.mmhh2.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by AbuAli on 8/2/2016.
 */
public class Operations {
    private static String ID, type, numberCharge, numberInquire;
    private static int typeNumberCharge, typeNumberInquire;

    public static void setVariables(String numberCard, String i, String t, Context context) {

        ID = i;
        type = t;
        try {
            switch (type) {
                case "stc":
                    typeNumberCharge = 155;
                    numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                    break;


                case "mobily":
                    typeNumberCharge = 1400;
                    numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                    break;

                case "zin":
                    typeNumberCharge = 141;
                    numberCharge = "*" + typeNumberCharge + "*" + numberCard + "*" + ID + "#";
                    break;

                default:
                    break;
            }
        } catch (Exception ex) {
            Toast.makeText(context, "لم تحدد نوع الشريحة", Toast.LENGTH_SHORT).show();

        }

    }


    public static void setVariables(String t, Context context) {
        try {
            type = t;
            switch (type) {
                case "stc":
                    typeNumberInquire = 166;
                    numberInquire = "*" + typeNumberInquire + "#";
                    break;


                case "mobily":
                    typeNumberInquire = 1411;
                    numberInquire = "*" + typeNumberInquire + "#";
                    break;

                case "zin":
                    typeNumberInquire = 142;
                    numberInquire = "*" + typeNumberInquire + "#";
                    break;

                default:
                    break;
            }
        } catch (Exception ex) {
            Toast.makeText(context, "لم تحدد نوع الشريحة", Toast.LENGTH_SHORT).show();

        }


    }

    public static Intent call(String number) {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", number, null));
        return i;
    }

    public static String getNumberCharge() {
        return numberCharge;
    }

    public static String getNumberInquire() {
        return numberInquire;
    }

    public static boolean checkCharge(Context context, String numberCard) {
        try {


            if (ID == null) {
                Toast.makeText(context, "لا يوجد رقم هوية", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (ID.length() == 10) {
                    if (!type.equals(null)) {
                        if (!numberCard.equals(null)) {
                            if (numberCard.matches("[0-9]+")) {
                                if (numberCard.length() >= 14) {
                                    return true;

                                } else {
                                    Toast.makeText(context, "رقم البطاقة قصير جدا", Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            } else {
                                Toast.makeText(context, "لا يوجد رقم بطاقة", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                        } else {

                            Toast.makeText(context, "لا يوجد رقم بطاقة", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    }

                } else {
                    Toast.makeText(context, "رقم الهوية غير صحيح", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return false;
        } catch (Exception ex) {
            Toast.makeText(context, "لم تحدد نوع الشريحة", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean checkCheck(Context context) {
        try {


            if (!type.equals(null)) {
                return true;
            }
        } catch (Exception ex) {
            Toast.makeText(context, "لم تحدد نوع الشريحة", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }
}
