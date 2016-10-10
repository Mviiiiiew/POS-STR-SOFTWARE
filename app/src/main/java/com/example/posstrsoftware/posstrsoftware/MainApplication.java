package com.example.posstrsoftware.posstrsoftware;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by Wasabi on 10/10/2016.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
