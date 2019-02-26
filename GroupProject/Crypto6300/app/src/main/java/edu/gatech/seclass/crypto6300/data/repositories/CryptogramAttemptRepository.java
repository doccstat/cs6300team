package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;

import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramAttemptDao;

public class CryptogramAttemptRepository {

    private CryptogramAttemptDao cryptogramAttemptDao;

    public CryptogramAttemptRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        cryptogramAttemptDao = db.cryptogramAttemptDao();
    }
}
