package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;

public class confirmation extends AppCompatActivity {

    HashMap<String, String> contextData = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);

        Intent checkOutPage = getIntent();

        contextData.clear();
        contextData.put("&&products", checkOutPage.getStringExtra("productString"));
        MobileCore.trackState("Confirmation Page", contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}