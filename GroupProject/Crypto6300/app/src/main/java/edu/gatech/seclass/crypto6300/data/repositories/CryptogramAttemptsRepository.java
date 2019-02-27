package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramAttemptDao;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;

public class CryptogramAttemptsRepository {

    private CryptogramAttemptDao cryptogramAttemptDao;

    public CryptogramAttemptsRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        cryptogramAttemptDao = db.cryptogramAttemptDao();
    }

    public LiveData<List<CryptogramAttempt>> getAllAttemptsForCryptogram(String cryptogramId) {
        return cryptogramAttemptDao.getAllAttemptsForCryptogram(cryptogramId);
    }

    public LiveData<List<CryptogramAttempt>> getAllAttemptsForPlayer(String playerId) {
        return cryptogramAttemptDao.getAllAttemptsForPlayer(playerId);
    }

    public LiveData<List<CryptogramAttempt>> getAllAttempts() {
        return cryptogramAttemptDao.getAllCryptogramAttempts();
    }

    public LiveData<CryptogramAttempt> getAttemptById(String attemptId) {
        return cryptogramAttemptDao.getAttemptById(attemptId);
    }

    public void insert(CryptogramAttempt attempt) {
        new insertAttemptAsyncTask(cryptogramAttemptDao).execute(attempt);
    }

    public void update(CryptogramAttempt attempt) {
        new updateAttemptAsyncTask(cryptogramAttemptDao).execute(attempt);
    }

    public void deleteAllAttemptsForPlayer(String playerId) {
        new deleteAllAttemptsForPlayerAsyncTask(cryptogramAttemptDao).execute(playerId);
    }

    public void deleteAllAttemptsForCryptogram(String cryptogramId) {
        new deleteAllAttemptsForCryptogramAsyncTask(cryptogramAttemptDao).execute(cryptogramId);
    }

    public void deleteAllAttempts() {
        new deleteAllAttemptsAsyncTask(cryptogramAttemptDao).execute();
    }

    private class insertAttemptAsyncTask extends AsyncTask<CryptogramAttempt, Void, Void> {

        private CryptogramAttemptDao attemptDao;

        insertAttemptAsyncTask(CryptogramAttemptDao attemptDao) {
            this.attemptDao = attemptDao;
        }


        @Override
        protected Void doInBackground(CryptogramAttempt... cryptogramAttempts) {
            attemptDao.insertAttempt(cryptogramAttempts[0]);
            return null;
        }
    }

    private class updateAttemptAsyncTask extends AsyncTask<CryptogramAttempt, Void, Void> {

        private CryptogramAttemptDao attemptDao;

        updateAttemptAsyncTask(CryptogramAttemptDao attemptDao) {
            this.attemptDao = attemptDao;
        }


        @Override
        protected Void doInBackground(CryptogramAttempt... cryptogramAttempts) {
            attemptDao.updateAttempt(cryptogramAttempts[0]);
            return null;
        }
    }

    private class deleteAllAttemptsForPlayerAsyncTask extends AsyncTask<String, Void, Void> {

        private CryptogramAttemptDao attemptDao;

        deleteAllAttemptsForPlayerAsyncTask(CryptogramAttemptDao attemptDao) {
            this.attemptDao = attemptDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            attemptDao.deleteAllAttemptsForPlayer(strings[0]);
            return null;
        }
    }

    private class deleteAllAttemptsForCryptogramAsyncTask extends AsyncTask<String, Void, Void> {

        private CryptogramAttemptDao attemptDao;

        deleteAllAttemptsForCryptogramAsyncTask(CryptogramAttemptDao attemptDao) {
            this.attemptDao = attemptDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            attemptDao.deleteAllAttemptsForCryptogram(strings[0]);
            return null;
        }
    }

    private class deleteAllAttemptsAsyncTask extends AsyncTask<CryptogramAttempt, Void, Void> {

        private CryptogramAttemptDao attemptDao;

        deleteAllAttemptsAsyncTask(CryptogramAttemptDao attemptDao) {
            this.attemptDao = attemptDao;
        }

        @Override
        protected Void doInBackground(CryptogramAttempt... cryptogramAttempts) {
            attemptDao.deleteAllAttempts();
            return null;
        }
    }


}
