package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    // Administrator functions
    public void createPlayer(String firstname, String lastname, String username, int category) {
        // TODO
    }

    public boolean createCryptogram(String name, String solution, int[] maxAttempts) {
        // TODO
        return false;
    }

    public boolean saveCryptogram(String name, String solution, int difficulty, int maxNumEasyAttempts, int maxNumNormalAttempts, int maxNumHardAttempts) {
        // TODO
        return false;
    }


    // Player functions
    public Cryptogram getCryptogram(String cryptogramName) {
        // TODO
        return null;
    }

    public void saveCurrentSubmission(String submission) {
        // TODO
    }

    public boolean solveCryptogram(String submission) {
        // TODO
        return false;
    }

    public int getWonCryptograms() {
        // TODO
        return 0;
    }

    public int getLostCryptograms() {
        // TODO
        return 0;
    }


    // CRUD functions
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
