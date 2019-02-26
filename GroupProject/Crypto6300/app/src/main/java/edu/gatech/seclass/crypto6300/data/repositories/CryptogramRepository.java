package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;

import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramDao;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;

public class CryptogramRepository {

    private CryptogramDao cryptogramDao;

    public CryptogramRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        cryptogramDao = db.cryptogramDao();
    }

    public void insert(Cryptogram cryptogram) {

    }

    public void deleteById(String cryptogramId) {

    }

    public void getAllCryptograms() {

    }
}
