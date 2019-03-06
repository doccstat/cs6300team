package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class SolveCryptogramFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CryptogramRepository cryptogramRepository;
    private CryptogramAttemptsRepository attemptsRepository;

    public SolveCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        cryptogramRepository = new CryptogramRepository(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }

    public LiveData<Integer >CryptogramAttempts(@NonNull String userId,@NonNull String attemptId){
        return attemptsRepository.CryptogramAttempts(userId,attemptId);
    }

    public LiveData<CryptogramAttempt> getEncryptedPhrase(@NonNull String attemptId) {
        return attemptsRepository.getAttemptById(attemptId);
    }

    public LiveData<Boolean> submitSolution(@NonNull String attemptId, @NonNull String solution) {
        return attemptsRepository.checkSolution(attemptId, solution);
    }

    public LiveData<Boolean> checkIfAttemptComplete(String attemptId) {
        return attemptsRepository.checkIfAttemptCompleted(attemptId);
    }

    public void updateAttemptForTry(
            @NonNull String attemptId,
            @NonNull String submission,
            boolean isSolved,
            CryptogramAttemptsRepository.updateAttemptForTryAsyncTask.UpdateTryResponse response) {
        attemptsRepository.updateAttemptForTry(attemptId, submission, isSolved, response);
    }

    public void updateUserWinLossRecord(@NonNull String userId, boolean isWin) {
        userRepository.updateUserWinLossRecord(userId, isWin);
    }

}
