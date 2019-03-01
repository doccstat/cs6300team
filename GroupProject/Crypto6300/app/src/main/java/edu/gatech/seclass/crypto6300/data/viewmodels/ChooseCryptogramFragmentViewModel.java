package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class ChooseCryptogramFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CryptogramRepository cryptogramRepository;
    private CryptogramAttemptsRepository attemptsRepository;

    public ChooseCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        cryptogramRepository = new CryptogramRepository(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }

    public LiveData<List<ChooseCryptogram>> getList(String playerId) {
        return attemptsRepository.getChooseList(playerId);
    }
}
