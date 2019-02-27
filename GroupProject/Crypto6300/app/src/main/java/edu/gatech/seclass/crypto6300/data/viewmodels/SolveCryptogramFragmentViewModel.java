package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class SolveCryptogramFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CryptogramAttemptsRepository attemptsRepository;

    public SolveCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }
}
