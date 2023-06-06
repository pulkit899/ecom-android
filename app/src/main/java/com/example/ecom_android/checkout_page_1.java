package com.example.ecom_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.adobe.marketing.mobile.MobileCore;

import java.util.HashMap;

public class checkout_page_1 extends AppCompatActivity
{

    HashMap contextData = new HashMap<String,String>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page1);

        Intent checkoutPage = getIntent();
        final String[] values = checkoutPage.getStringArrayExtra("allValues");
        Intent goToConfirmation = new Intent(getApplicationContext(), confirmation.class);

        ImageView qImageView = (ImageView)findViewById(R.id.imageView3);
        TextView productName = (TextView)findViewById(R.id.textView2);
        TextView quantity =(TextView)findViewById(R.id.textView3);
        TextView price =(TextView)findViewById(R.id.textView4);
        Button order =(Button)findViewById(R.id.button2);

        if (values[0].equals(getString(R.string.Phone_2)))
            qImageView.setImageResource(R.drawable.oneplus_9);

        else if (values[0].equals(getString(R.string.Phone_1)))
            qImageView.setImageResource(R.drawable.poco_c50);

        productName.setText(values[0]);
        price.setText("Price: "+values[1]);
        quantity.setText("Quantity: "+values[2]);

        order.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                contextData.put("d.prodEvents", "purchase");
                contextData.put("&&products", values[3]);
                MobileCore.trackAction("Place order button click", contextData);

                goToConfirmation.putExtra("productString", values[3]);
                startActivity(goToConfirmation);
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Intent checkoutPage = getIntent();
        final String[] values = checkoutPage.getStringArrayExtra("allValues");

        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);

        contextData.clear();
        contextData.put("d.page", R.string.pagename_checkout);
        contextData.put("&&products", values[3]);
        MobileCore.trackState("Checkout", contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}