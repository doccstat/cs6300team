package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.UserDao;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> playerList;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getInstance(application);
        userDao = db.userDao();
        playerList = userDao.getPlayers();
    }

    public void insert(User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getPlayerList() {
        return playerList;
    }

    public LiveData<User> getUserById(String userId) {
        return userDao.getById(userId);
    }

    public LiveData<User> getUserByLoginInfo(String username, String password) {
        return userDao.getUserByLoginInfo(username, password);
    }

    public void deleteUser(User user) {
        new deleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers() {
        new deleteAllUsersAsyncTask(userDao).execute();
    }

    public void deleteAllPlayers() {
        new deleteAllPlayersAsyncTask(userDao).execute();
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public void updateUserWinLossRecord(String userId, boolean isWin) {
        new updateUserWinLossRecordAsyncTask(userDao).execute(userId, isWin);
    }

    /*
    ###############################

        Async Tasks

    ###############################
     */
    public static class updateUserWinLossRecordAsyncTask extends AsyncTask<Object, Void, Void> {

        private UserDao userDao;

        public updateUserWinLossRecordAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Object... params) {
            String userId = (String) params[0];
            boolean isWin = (boolean) params[1];

            if (isWin) {
                userDao.updateUserWin(userId);
            } else {
                userDao.updateUserLoss(userId);
            }

            return null;
        }
    }

    private class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        insertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private class deleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        public deleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

    private class deleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        public deleteAllPlayersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllPlayers();
            return null;
        }
    }

    private class deleteUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        deleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

}
