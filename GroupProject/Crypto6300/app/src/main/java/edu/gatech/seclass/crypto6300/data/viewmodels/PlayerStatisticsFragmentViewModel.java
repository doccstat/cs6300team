package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;

public class PlayerStatisticsFragmentViewModel extends AndroidViewModel {

    private CryptogramAttemptsRepository attemptsRepository;

    public PlayerStatisticsFragmentViewModel(@NonNull Application application) {
        super(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }
}
