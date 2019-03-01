package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class PlayerStatisticsFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CryptogramAttemptsRepository attemptsRepository;

    public PlayerStatisticsFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }

    public LiveData<List<User>> getPlayerStatistics() {
        return userRepository.getPlayerList();
    }
}
