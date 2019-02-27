package edu.gatech.seclass.crypto6300.data.view_models;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;

public class CryptogramAttemptViewModel extends AndroidViewModel {

    private CryptogramAttemptsRepository repository;

    public CryptogramAttemptViewModel(Application application) {
        super(application);
        repository = new CryptogramAttemptsRepository();
    }

    public void insert(CryptogramAttempt cryptogramAttempt) {
        repository.insert(cryptogramAttempt);
    }

    public void update(CryptogramAttempt cryptogramAttempt) {
        repository.update(cryptogramAttempt);
    }

    public void deleteByCryptogramId(String cryptogramId) {
        repository.deleteAllAttemptsForCryptogram(cryptogramId);
    }

    public void deleteAllAttemptsForPlayer(String playerId) {
        repository.deleteAllAttemptsForPlayer(playerId);
    }

    public void deleteAllAttempts() {
        repository.deleteAllAttempts();
    }

    public LiveData<CryptogramAttempt> getAttemptById(String attemptId) {
        return repository.getAttemptById(attemptId);
    }

    public LiveData<List<CryptogramAttempt>> getAllAttempts() {
        return repository.getAllAttempts();
    }

    public LiveData<List<CryptogramAttempt>> getAllAttemptsForPlayer(String playerId) {
        return repository.getAllAttemptsForPlayer(playerId);
    }

}
