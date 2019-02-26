package edu.gatech.seclass.crypto6300.data.repositories;

import android.app.Application;

import java.util.List;

import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.dao.UserDao;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.Companion.getINSTANCE();
        userDao = db.userDao();
    }

    public void addUser() {

    }

    public List<User> getAllPlayers() {
        return null;
    }

}
