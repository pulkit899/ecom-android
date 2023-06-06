package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

public class Setter extends AppCompatActivity
{

    public String[] setValues(String phoneName)
    {

        if (phoneName.equals("Poco C50"))
        {
            String productPrice = "$250";
            String productString = "Mobile Devices;"+phoneName+";1;"+productPrice+";;";
            String quantity = "1";

            String allValues[] = new String[] {phoneName, productPrice, quantity, productString};
            System.out.println(allValues);

            return allValues;
        }

        if (phoneName.equals("OnePlus 9"))
        {
            String productPrice = "$200";//getString(R.string.oneplus_price);
            String productString = "Mobile Devices;"+phoneName+";1;"+productPrice+";;";
            String quantity = "1";

            String allValues[] = new String[] {phoneName, productPrice, quantity, productString};
            System.out.println(allValues);
            return allValues;
        }
        return new String[0];
    }
}
