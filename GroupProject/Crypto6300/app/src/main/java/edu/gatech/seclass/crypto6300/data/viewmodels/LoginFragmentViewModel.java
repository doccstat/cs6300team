package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class LoginFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private User user;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public boolean login(String username, String password) {
        user = userRepository.getUserByLoginInfo(username, password).getValue();
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }
}
