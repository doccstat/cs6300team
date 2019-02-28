package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;

public class CryptogramViewModel extends AndroidViewModel {

    private CryptogramRepository repository;

    public CryptogramViewModel(Application application) {
        super(application);
        repository = new CryptogramRepository(application);
    }

    private CryptogramAttempt generateAttemptForUser(String username) {
        // TODO
        return null;
    }

    public Boolean checkSolution(String playerSolution) {
        // TODO
        return false;
    }


    // CRUD functions
    public void insert(Cryptogram cryptogram) {
        repository.insert(cryptogram);
    }

    public LiveData<List<Cryptogram>> getAllCryptograms() {
        return repository.getAllCryptograms();
    }

    public LiveData<List<Cryptogram>> getAllCryptogramsByDifficulty(String difficulty) {
        return repository.getAllCryptogramsByDifficulty(difficulty);
    }

    public LiveData<Cryptogram> getCryptogramById(String cryptogramId) {
        return repository.getCryptogramById(cryptogramId);
    }

    public void deleteAllCryptograms() {
        repository.deleteAllCryptograms();
    }
}
