package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.CryptogramAttemptDao;
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;

public class CryptogramAttemptsRepository {

    private CryptogramAttemptDao cryptogramAttemptDao;

    public CryptogramAttemptsRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getInstance(application);
        cryptogramAttemptDao = db.cryptogramAttemptDao();
    }

    public LiveData<Integer> CryptogramAttempts(String playerId, String cryptogramId){
        return cryptogramAttemptDao.getAttemptsRemaining(playerId, cryptogramId);
    }

    public LiveData<List<CryptogramAttempt>> getAllAttemptsForCryptogram(String cryptogramId) {
        return cryptogramAttemptDao.getAllAttemptsForCryptogram(cryptogramId);
    }

    public LiveData<List<ChooseCryptogram>> getChooseList(String playerId) {
        return cryptogramAttemptDao.getAttemptsAndUnsolvedCryptogramsForPlayer(playerId);
    }

    public LiveData<CryptogramAttempt> getAttemptByUserIdAndCryptogramId(String playerId, String cryptogramId) {
        return cryptogramAttemptDao.getAttemptByUserIdAndCryptogramId(playerId, cryptogramId);
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

    public LiveData<Boolean> checkSolution(String attemptId, String solution) {
        return cryptogramAttemptDao.checkSolutionForAttempt(attemptId, solution);
    }

    public LiveData<Boolean> checkIfAttemptCompleted(String attemptId) {
        return cryptogramAttemptDao.checkIfAttemptCompleted(attemptId);
    }

    public void updateAttemptForTry(String attemptId, String submission, boolean isSolved, updateAttemptForTryAsyncTask.UpdateTryResponse response) {
        new updateAttemptForTryAsyncTask(cryptogramAttemptDao, response).execute(attemptId, submission, isSolved);
    }

    public void insert(CryptogramAttempt attempt, insertAttemptAsyncTask.InsertResponse response) {
        new insertAttemptAsyncTask(cryptogramAttemptDao, response).execute(attempt);
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

    /*
    ###############################

        Async Tasks

    ###############################
     */
    public static class insertAttemptAsyncTask extends AsyncTask<CryptogramAttempt, Void, String> {

        public interface InsertResponse {
            void processFinished(String attemptId);
        }

        private CryptogramAttemptDao attemptDao;
        private InsertResponse delegate;

        insertAttemptAsyncTask(CryptogramAttemptDao attemptDao, InsertResponse delegate) {
            this.attemptDao = attemptDao;
            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(CryptogramAttempt... cryptogramAttempts) {
            return String.valueOf(attemptDao.insertAttempt(cryptogramAttempts[0]));
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            delegate.processFinished(result);
        }
    }

    public static class updateAttemptForTryAsyncTask extends AsyncTask<Object, Void, Boolean> {

        public interface UpdateTryResponse {
            void updateTryFinished(Boolean isSolved);
        }

        private CryptogramAttemptDao attemptDao;
        private UpdateTryResponse delegate;

        updateAttemptForTryAsyncTask(CryptogramAttemptDao attemptDao, UpdateTryResponse delegate) {
            this.attemptDao = attemptDao;
            this.delegate = delegate;
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            String attemptId = (String) params[0];
            String submission = (String) params[1];
            boolean isSolved = (Boolean) params[2];
            attemptDao.updateAttemptForTry(attemptId, submission, isSolved);

            return isSolved;
        }

        @Override
        protected void onPostExecute(Boolean isSolved) {
            super.onPostExecute(isSolved);
            delegate.updateTryFinished(isSolved);
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
