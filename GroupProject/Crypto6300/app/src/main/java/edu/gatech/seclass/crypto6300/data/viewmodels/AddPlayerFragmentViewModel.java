package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class AddPlayerFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public AddPlayerFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
}
