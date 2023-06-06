package com.example.ecom_android;

import android.app.Application;
import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Analytics;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.InvalidInitException;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.MobileServices;

public class ApplicationClass extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        MobileCore.setApplication(this);
        MobileCore.setLogLevel(LoggingMode.VERBOSE);
        try
        {
            Analytics.registerExtension();
            Identity.registerExtension();
            MobileServices.registerExtension();
            Lifecycle.registerExtension();
            Signal.registerExtension();
            MobileCore.start(new AdobeCallback ()
            {
                @Override
                public void call(Object o)
                {
                    //MobileCore.configureWithAppID("e9875dd51dbe/a840929011eb/launch-034524de14b9-development");
                    MobileCore.configureWithAppID("cb203a95e68c/a15ac47c1ccb/launch-90fe834f290b-development");
                }
            });
        }
        catch (InvalidInitException E)
        {
            //
        }
    }

}