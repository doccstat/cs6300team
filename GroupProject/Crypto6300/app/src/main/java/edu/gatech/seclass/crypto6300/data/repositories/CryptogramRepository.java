package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramDao;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;

public class CryptogramRepository {

    private CryptogramDao cryptogramDao;
    private LiveData<Cryptogram> cryptogram;
    private LiveData<List<Cryptogram>> cryptogramList;

    public CryptogramRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        cryptogramDao = db.cryptogramDao();
        cryptogramList = cryptogramDao.getAllCryptograms();
    }

    public LiveData<List<Cryptogram>> getAllCryptograms() {
        return cryptogramList;
    }

    public LiveData<Cryptogram> getCryptogramById(String cryptogramId) {
        return cryptogramDao.getById(cryptogramId);
    }

    public void insert(Cryptogram cryptogram) {
        new insertCryptogramAsyncTask(cryptogramDao).execute(cryptogram);
    }

    public void deleteAllCryptograms() {
        new deleteAllCryptogramsAsyncTask(cryptogramDao).execute();
    }

    private class insertCryptogramAsyncTask extends AsyncTask<Cryptogram, Void, Void> {

        private CryptogramDao cryptogramDao;

        public insertCryptogramAsyncTask(CryptogramDao cryptogramDao) {
            this.cryptogramDao = cryptogramDao;
        }

        @Override
        protected Void doInBackground(Cryptogram... cryptograms) {
            cryptogramDao.insert(cryptograms[0]);
            return null;
        }
    }

    private class deleteCryptogramsAsyncTask extends AsyncTask<Cryptogram, Void, Void> {
        private CryptogramDao cryptogramDao;

        public deleteCryptogramsAsyncTask(CryptogramDao cryptogramDao) {
            this.cryptogramDao = cryptogramDao;
        }

        @Override
        protected Void doInBackground(Cryptogram... cryptograms) {
            cryptogramDao.delete(cryptograms[0]);
            return null;
        }
    }

    private class deleteAllCryptogramsAsyncTask extends AsyncTask<Void, Void, Void> {
        private CryptogramDao cryptogramDao;

        public deleteAllCryptogramsAsyncTask(CryptogramDao cryptogramDao) {
            this.cryptogramDao = cryptogramDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cryptogramDao.deleteAllCryptograms();
            return null;
        }
    }
}
