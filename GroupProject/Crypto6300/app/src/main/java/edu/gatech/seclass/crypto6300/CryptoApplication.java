package edu.gatech.seclass.crypto6300;

import android.app.Application;

import timber.log.Timber;

public class CryptoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
