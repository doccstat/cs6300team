package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.Attempts;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;

public class AddCryptogramFragmentViewModel extends AndroidViewModel {

    private CryptogramRepository cryptogramRepository;

    public AddCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        cryptogramRepository = new CryptogramRepository(application);
    }

    public LiveData<Cryptogram> getCryptogramForName(String name) {
        return cryptogramRepository.getCryptogramByName(name);
    }

    public void addCryptogram(@NonNull String name, @NonNull String solution, int difficulty, @NonNull Attempts attempts) {
        Cryptogram cryptogram = new Cryptogram(
                name,
                solution,
                difficulty,
                attempts
        );
        cryptogramRepository.insert(cryptogram);
    }
}
