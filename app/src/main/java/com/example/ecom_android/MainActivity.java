package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap contextData = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loginText = (EditText) findViewById(R.id.username);
        final EditText loginPassword = (EditText) findViewById(R.id.password);
        final Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (loginText.getText().toString().equals("admin") && loginPassword.getText().toString().equals("admin"))
                {
                    contextData.put("d.username", getString(R.string.username));
                    contextData.put("d.loginStatus", "successful");
                    MobileCore.trackAction("Login Successful", contextData);
                    startActivity(new Intent(getApplicationContext(), ProductListing.class));
                }
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);

        contextData.clear();
        contextData.put("d.page", getString(R.string.login_page));
        //contextData.put("d.manufacturer", Build.MANUFACTURER);
        MobileCore.trackState("Login Screen", contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}