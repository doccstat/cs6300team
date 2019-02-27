package edu.gatech.seclass.crypto6300.data.view_models;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramDao;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;

public class CryptogramViewModel {

    private CryptogramDao cryptogramDao;

    public CryptogramViewModel(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        cryptogramDao = db.cryptogramDao();
    }

    public void insert(Cryptogram cryptogram) {
        cryptogramDao.insert(cryptogram);
    }

    public void deleteById(Cryptogram cryptogram) {
        cryptogramDao.delete(cryptogram);
    }

    public LiveData<Cryptogram> getCryptogramById(String cryptogramId) {
        return cryptogramDao.getById(cryptogramId);
    }

    public LiveData<List<Cryptogram>> getAllCryptograms() {
        return cryptogramDao.getAllCryptograms();
    }
}
