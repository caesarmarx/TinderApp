package jxgi.com.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Caesar on 5/17/2017.
 */

public class TenderApplication extends MultiDexApplication {
    private static TenderApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }


    public static synchronized TenderApplication getInstance() {
        return mainApplication;
    }
}
