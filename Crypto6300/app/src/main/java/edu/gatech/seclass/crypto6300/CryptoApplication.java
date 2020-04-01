package edu.gatech.seclass.crypto6300;

import android.app.Application;

import com.facebook.stetho.Stetho;

import edu.gatech.seclass.crypto6300.data.AppDatabase;
import timber.log.Timber;

public class CryptoApplication extends Application {

    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        appDatabase = AppDatabase.Companion.getInstance(this);
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
