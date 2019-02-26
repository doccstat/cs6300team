package edu.gatech.seclass.crypto6300;

import android.app.Application;

import edu.gatech.seclass.crypto6300.data.AppDatabase;
import timber.log.Timber;

public class CryptoApplication extends Application {

    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        appDatabase = AppDatabase.Companion.getAppDb(this);
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
