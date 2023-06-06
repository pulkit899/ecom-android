package com.example.ecom_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.adobe.marketing.mobile.MobileCore;
import java.util.HashMap;

public class product_details extends AppCompatActivity {

    HashMap<String, String> contextData = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent plp = getIntent();
        final String[] values = plp.getStringArrayExtra("allValues");
        Intent goToCart =  new Intent(getApplicationContext(), cart.class);

        ImageView qImageView = (ImageView) findViewById(R.id.imageView);
        TextView productName = (TextView) findViewById(R.id.product_name);
        TextView price = (TextView) findViewById(R.id.product_price);
        Button buyNow = (Button) findViewById(R.id.buy_now);

        productName.setText(values[0]);
        price.setText(values[1]);

        if (values[0].equals(getString(R.string.Phone_1)))
            qImageView.setImageResource(R.drawable.poco_c50);

        if (values[0].equals(getString(R.string.Phone_2)))
            qImageView.setImageResource(R.drawable.oneplus_9);


        buyNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
        Intent plp = getIntent();
        final String[] values = plp.getStringArrayExtra("allValues");

        contextData.clear();
        contextData.put("&&products", values[3]);
        contextData.put("d.prodEvents", "prodview");
        contextData.put("d.page", getString(R.string.product_view_page));
        MobileCore.trackState(getString(R.string.product_view_page), contextData);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobileCore.lifecyclePause();
    }
}