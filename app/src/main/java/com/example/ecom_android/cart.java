package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;

public class cart extends AppCompatActivity
{
    HashMap contextData = new HashMap<String,String>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent cartPage = getIntent();
        final String[] values = cartPage.getStringArrayExtra("allValues");
        Intent goToCheckout = new Intent(getApplicationContext(), checkout_page_1.class);

        ImageView qImageView = (ImageView)findViewById(R.id.imageView3);
        TextView productName = (TextView)findViewById(R.id.textView2);
        TextView quantity =(TextView)findViewById(R.id.textView3);
        Button checkOut =(Button)findViewById(R.id.button2);


        productName.setText(values[1]);
        quantity.setText("Quantity: "+values[2]);

        if(cartPage.getStringExtra("buyType").equals("now"))
        {

            if (values[0].equals(getString(R.string.Phone_2)))
                qImageView.setImageResource(R.drawable.oneplus_9);


            if (values[0].equals(getString(R.string.Phone_1)))
                qImageView.setImageResource(R.drawable.poco_c50);

            checkOut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    contextData.clear();
                    contextData.put("d.prodEvents", "scCheckout");
                    contextData.put("&&products", values[3]);
                    MobileCore.trackAction("Checkout button click", contextData);

                    goToCheckout.putExtra("allValues", values);
                    startActivity(goToCheckout);
                }
            });
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Intent cartPage = getIntent();
        final String[] values = cartPage.getStringArrayExtra("allValues");

        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);

        contextData.clear();
        contextData.put("d.page", R.string.pagename_cart);
        contextData.put("d.prodEvents", "scView");
        contextData.put("&&products", values[3]);
        MobileCore.trackState(getString(R.string.pagename_cart), contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}