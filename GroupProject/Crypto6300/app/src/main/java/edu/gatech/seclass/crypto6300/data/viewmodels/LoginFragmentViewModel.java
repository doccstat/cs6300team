package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class LoginFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public boolean login(String username, String password) {
        userRepository.getUserByLoginInfo(username, password).getValue();

        // TODO
        return false;
    }
}
