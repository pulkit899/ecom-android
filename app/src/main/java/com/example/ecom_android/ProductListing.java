package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;

import com.adobe.marketing.mobile.MobileCore;
import java.util.HashMap;

public class ProductListing extends AppCompatActivity
{
    HashMap<String, String> contextData = new HashMap<String,String>();
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        final ImageButton buyButtonOnePlus = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton buyButtonPoco = (ImageButton) findViewById(R.id.imageButton4);
        final ImageView oneplus = (ImageView) findViewById(R.id.oneplus);
        final ImageView poco_c50 = (ImageView) findViewById(R.id.poco_c50);

        Setter setter = new Setter();

        Intent goToCart =  new Intent(getApplicationContext(), cart.class);
        Intent goToProduct =  new Intent(getApplicationContext(), product_details.class);

        oneplus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                values = setter.setValues("OnePlus 9");

                contextData.clear();
                contextData.put("&&products", values[3]);
                MobileCore.trackAction("Product Image Click", contextData);

                goToProduct.putExtra("allValues", values);
                startActivity(goToProduct);
            }
        });

        poco_c50.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                values = setter.setValues("Poco C50");

                contextData.clear();
                contextData.put("&&products", values[3]);
                MobileCore.trackAction("Product Image Click", contextData);

                goToProduct.putExtra("allValues", values);
                startActivity(goToProduct);
            }
        });

        buyButtonOnePlus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                values = setter.setValues("Poco C50");

                contextData.clear();
                contextData.put("d.prodEvents", "scAdd");
                contextData.put("&&products", values[3]);
                MobileCore.trackAction("Buy now button click", contextData);

                goToCart.putExtra("allValues", values);
                goToCart.putExtra("buyType", "now");
                startActivity(goToCart);
            }
        });

        buyButtonPoco.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                values = setter.setValues("OnePlus 9");

                contextData.clear();
                contextData.put("d.prodEvents", "scAdd");
                contextData.put("&&products", values[3]);
                MobileCore.trackAction("Buy now button click", contextData);

                goToCart.putExtra("allValues", values);
                goToCart.putExtra("buyType", "now");
                startActivity(goToCart);
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
        contextData.put("&&products", "Mobile Devices;"+getString(R.string.Phone_2)+";;;;,"+"Mobile Devices;"+getString(R.string.Phone_1)+";;;;");
        contextData.put("d.prodEvents", "impression");
        contextData.put("d.page", getString(R.string.productlist));
        MobileCore.trackState(getString(R.string.productlist), contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}