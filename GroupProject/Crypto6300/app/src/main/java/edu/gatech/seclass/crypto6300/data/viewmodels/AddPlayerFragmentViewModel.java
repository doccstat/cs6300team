package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class AddPlayerFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public AddPlayerFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getUserForUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void addPlayer(
            @NonNull String firstname,
            @NonNull String lastname,
            @NonNull String username,
            @NonNull String password,
            int category
    ) {
        User user = new User(
                firstname,
                lastname,
                username,
                password,
                category,
                0,
                0
        );
        userRepository.insert(user);
    }
}
