package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class LoginFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private User user;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> login(String username, String password) {
        return userRepository.getUserByLoginInfo(username, password);
    }

    public User getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }
}
