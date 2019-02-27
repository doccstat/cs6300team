package edu.gatech.seclass.crypto6300.data.view_models;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository();
    }

    public void insertUser(User user) {
        userRepository.insert(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    public void deleteAllUser() {
        userRepository.deleteAllUsers();
    }

    public void deleteAllPlayers() {
        userRepository.deleteAllPlayers();
    }

    public LiveData<User> getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    public LiveData<User> getUserByLoginInfo(String username, String password) {
        return userRepository.getUserByLoginInfo(username, password);
    }

    public LiveData<List<User>> getAllPlayers() {
        return userRepository.getPlayerList();
    }
}
